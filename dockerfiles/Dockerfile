ARG UV_TAG=0.9.10
# ARG JDK_TAG=21

FROM ghcr.io/astral-sh/uv:${UV_TAG} AS uv-builder
# FROM openjdk:${JDK_TAG} AS java-builder

FROM ubuntu:24.04


# ============================================================
# 「基础」软件列表 BASE_INSTALL_LIST
#        - ubuntu-desktop-minimal   桌面环境（会比较大）
#        - ubuntu-server            服务器环境 (包含 openssh-server ?)
#        - ca-certificates          证书
#        - locales                  语言环境
#        - zsh                      shell
# ============================================================
ARG BASE_INSTALL_LIST="ubuntu-server"
ARG BASE_INSTALL_LIST="$BASE_INSTALL_LIST ca-certificates locales"
ARG BASE_INSTALL_LIST="$BASE_INSTALL_LIST zsh"

# ============================================================
# 「扩展」软件列表 EXTENT_INSTALL_LIST
#        - vim nano          编辑器 (ubuntu-server 包含 vi)
#        - tree              目录树
#        - pciutils          PCI 设备信息
#        - iputils-ping      ping 命令
#        - bash-completion   bash 补全 (zsh 取代了)
#        - dotenv            环境变量管理
#        - git               版本控制工具
#        - openssh-client    SSH 客户端 (ubuntu-server 不包含，所以如果使用 git clone 需要安装)
# ============================================================
ARG EXTENT_INSTALL_LIST=""
ARG EXTENT_INSTALL_LIST="$EXTENT_INSTALL_LIST vim nano tree pciutils iputils-ping dnsutils"
ARG EXTENT_INSTALL_LIST="$EXTENT_INSTALL_LIST git openssh-client"
ARG EXTENT_INSTALL_LIST="$EXTENT_INSTALL_LIST build-essential"
# ARG EXTENT_INSTALL_LIST="$EXTENT_INSTALL_LIST cmake ninja-build clangd"
ARG EXTENT_INSTALL_LIST="$EXTENT_INSTALL_LIST python3-dev"
ARG EXTENT_INSTALL_LIST="$EXTENT_INSTALL_LIST postgresql-client"
ARG EXTENT_INSTALL_LIST="$EXTENT_INSTALL_LIST redis-tools"
ARG EXTENT_INSTALL_LIST="$EXTENT_INSTALL_LIST default-jdk maven"

# 自定义镜像源
# ARG MIRRORS_URL="mirrors.ustc.edu.cn"
ARG MIRRORS_URL="mirrors.tencentyun.com"

# 是否清理 apt 缓存
ARG CLEAN_APT_CACHE=1

# 安装软件
ENV DEBIAN_FRONTEND=noninteractive
ENV INSTALL_LIST="$BASE_INSTALL_LIST $EXTENT_INSTALL_LIST"
# set -x 选项会使得每个命令在执行前都会被打印出来，报错时会显示是哪个命令出错
RUN set -x && \
    sed -i 's/#force_color_prompt=yes/force_color_prompt=yes/g' ${HOME}/.bashrc && \
    sed -i -e "s#//.*archive.ubuntu.com#//${MIRRORS_URL}#g" -e "s#//ports.ubuntu.com#//${MIRRORS_URL}#g" /etc/apt/sources.list.d/ubuntu.sources && \
    apt-get update && apt-get upgrade -y && \
    apt-get install -y --no-install-recommends ${INSTALL_LIST} && \
    apt-get autoremove -y && \
    if [ $CLEAN_APT_CACHE -ne 1 ]; then { apt-get clean all; rm -rf /var/lib/apt/lists/*; } fi && \
    localedef -i zh_CN -c -f UTF-8 -A /usr/share/locale/locale.alias zh_CN.UTF-8 

ENV LANG=zh_CN.utf8

# 系统时间错误可能导致: `tls: failed to verify certificate: x509: certificate signed by unknown authority`
# 修改时区 (date -R 检查当前时间)
RUN ln -sf /usr/share/zoneinfo/Asia/Shanghai /etc/localtime

# 启用 bash 补全
# RUN sed -i.bak '/^#if ! shopt -oq posix; then$/,/^#fi$/s/^#//' /etc/bash.bashrc

# 设置 zsh 为默认 shell
RUN set -x && \
    chsh -s /usr/bin/zsh && \
    git clone https://mirrors.tuna.tsinghua.edu.cn/git/ohmyzsh.git --depth 1 && \
    cd ohmyzsh/tools && \
    REMOTE=https://mirrors.tuna.tsinghua.edu.cn/git/ohmyzsh.git sh install.sh && \
    # git clone https://github.com/zsh-users/zsh-autosuggestions ${ZSH_CUSTOM:-/root/.oh-my-zsh/custom}/plugins/zsh-autosuggestions && \
    # 使用 gitee 同步仓库
    git clone https://gitee.com/henryzhuhr/zsh-autosuggestions.git ${ZSH_CUSTOM:-/root/.oh-my-zsh/custom}/plugins/zsh-autosuggestions && \
    echo "ZSH_THEME='candy'" >> ~/.zshrc && \
    echo "plugins=(macos dotenv git cp python bundler rake rbenv ruby zsh-autosuggestions)" >> ~/.zshrc && \
    echo "ZSH_AUTOSUGGEST_HIGHLIGHT_STYLE='fg=#00BFFF,bg=gray,bold,underline'" >> ~/.zshrc && \
    echo "ZSH_AUTOSUGGEST_STRATEGY=(history completion)" >> ~/.zshrc && \
    echo "source \$ZSH/oh-my-zsh.sh" >> ~/.zshrc && \
    echo "alias ll='ls -alh'" >> ~/.zshrc && \
    echo "alias la='ls -A'" >> ~/.zshrc && \
    echo "alias l='ls -CF'" >> ~/.zshrc && \
    echo "alias h='history'" >> ~/.zshrc && \
    echo "alias mkdir='mkdir -p'" >> ~/.zshrc && \
    echo "alias grep='grep --color=auto'" >> ~/.zshrc && \
    echo "alias egrep='egrep --color=auto'" >> ~/.zshrc && \
    echo "alias fgrep='fgrep --color=auto'" >> ~/.zshrc

ARG USER_NAME="root"
ARG USER_HOME="/${USER_NAME}"

# ============================================================
#   ⬇️ 扩展 Dockerfile
# ============================================================

# ============================================================
#   Java 环境
# ============================================================

RUN echo "export JAVA_HOME=/usr/lib/jvm/default-java" >> $USER_HOME/.zshrc && \
  echo "export PATH=${JAVA_HOME}/bin:${PATH}" >> $USER_HOME/.zshrc && \
  echo "export M2_HOME=/usr/share/maven" >> $USER_HOME/.zshrc && \
  echo "export MAVEN_HOME=/usr/share/maven" >> $USER_HOME/.zshrc && \
  echo "export PATH=${M2_HOME}/bin:${PATH}" >> $USER_HOME/.zshrc && \
  # 修改 Maven settings.xml 配置，添加阿里云镜像源 (https://maven.apache.org/settings.html)
  export SETTINGS_FILE="/usr/share/maven/conf/settings.xml" && \
  mkdir -p "$(dirname "$SETTINGS_FILE")" && \
  if [ -f ${SETTINGS_FILE} ]; then \
    # 在<mirrors>标签下添加阿里云镜像配置
    sed -i '/<mirrors>/!b' -e '/<mirrors>/a\  <!-- https://developer.aliyun.com/mvn/guide --><mirror><id>aliyunmaven</id><mirrorOf>*</mirrorOf><name>阿里云公共仓库</name><url>https://maven.aliyun.com/repository/public</url></mirror>' ${SETTINGS_FILE}; \
    sed -i '/<mirrors>/!b' -e '/<mirrors>/a\  <!-- https://mirrors.cloud.tencent.com/help/maven.html --><mirror><id>nexus-tencentyun</id><mirrorOf>*</mirrorOf><name>Nexus tencentyun</name><url>http://mirrors.cloud.tencent.com/nexus/repository/maven-public/</url></mirror>' ${SETTINGS_FILE}; \
    # 如果<mirrors>标签不存在，则创建并添加配置
    if ! grep -q '<mirrors>' ${SETTINGS_FILE}; then \
      sed -i '/<settings>/a\  <mirrors>\n  </mirrors>' ${SETTINGS_FILE}; \
      sed -i '/<mirrors>/a\  <!-- https://developer.aliyun.com/mvn/guide --><mirror><id>aliyunmaven</id><mirrorOf>*</mirrorOf><name>阿里云公共仓库</name><url>https://maven.aliyun.com/repository/public</url></mirror>' ${SETTINGS_FILE}; \
      sed -i '/<mirrors>/a\  <!-- https://mirrors.cloud.tencent.com/help/maven.html --><mirror><id>nexus-tencentyun</id><mirrorOf>*</mirrorOf><name>Nexus tencentyun</name><url>http://mirrors.cloud.tencent.com/nexus/repository/maven-public/</url></mirror>' ${SETTINGS_FILE}; \  
   fi; \
  else \
    # 如果文件不存在，直接创建新的settings.xml
    echo "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n<settings>\n  <mirrors>\n    <mirror>\n      <id>aliyunmaven</id>\n      <mirrorOf>central</mirrorOf>\n      <name>aliyun maven</name>\n      <url>https://maven.aliyun.com/repository/public</url>\n    </mirror>\n  </mirrors>\n</settings>" > ${SETTINGS_FILE}; \
  fi

# ============================================================
#   Python 环境
# ============================================================
# # PEP 668 – Python base environments Python 增强提案 (PEP)：https://realpython.com/python-virtual-environments-a-primer/?ref=yaolong.net
# # https://www.adamsdesk.com/posts/resolve-pip-externally-managed-environment
# # 临时解决方案  --ignoreExternallyManagedEnvironment
# 使用 uv
COPY --from=uv-builder /uv /uvx /bin/

# 默认使用系统 Python 环境
ENV UV_SYSTEM=true

ENV UV_DEFAULT_INDEX="https://mirrors.cloud.tencent.com/pypi/simple/"
# ENV UV_INDEX="https://mirrors.ustc.edu.cn/pypi/simple"
RUN set -x && \
    # 在 uv 没有支持全局变量前，临时加入 history 文件
    echo ": $(date +%s):0;uv add " >> $USER_HOME/.zsh_history && \
    echo ": $(date +%s):0;uv run " >> $USER_HOME/.zsh_history && \
    echo ": $(date +%s):0;uv sync" >> $USER_HOME/.zsh_history && \
    echo "finished"
