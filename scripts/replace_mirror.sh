#!/bin/bash

# 定义 settings.xml 文件路径
SETTINGS_FILE="$HOME/.m2/settings.xml"

# 定义阿里云镜像配置
ALIYUN_MIRROR_CONFIG="
<mirror>
    <id>aliyunmaven</id>
    <mirrorOf>central</mirrorOf>
    <name>Aliyun Maven</name>
    <url>https://maven.aliyun.com/repository/public</url>
</mirror>
"

# 检查文件是否存在，如果不存在则创建
if [ ! -f "$SETTINGS_FILE" ]; then
    mkdir -p "$(dirname "$SETTINGS_FILE")"
    echo "<settings xmlns=\"http://maven.apache.org/SETTINGS/1.0.0\"
          xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"
          xsi:schemaLocation=\"http://maven.apache.org/SETTINGS/1.0.0 http://maven.apache.org/xsd/settings-1.0.0.xsd\">
</settings>" > "$SETTINGS_FILE"
fi

# 检查是否已经存在 aliyunmaven 配置
if grep -q "<id>aliyunmaven</id>" "$SETTINGS_FILE"; then
    echo "阿里云镜像已存在，无需重复添加。"
else
    # 插入阿里云镜像配置
    sed -i '/<mirrors>/a '"$(echo "$ALIYUN_MIRROR_CONFIG" | sed 's/$/\\n/')" "$SETTINGS_FILE"
    echo "阿里云镜像配置已成功添加到 $SETTINGS_FILE"
fi