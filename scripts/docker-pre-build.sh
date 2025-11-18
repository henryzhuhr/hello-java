#!/bin/bash
# 预先构建项目镜像的脚本，加快 docker compose up 的速度

IMAGE_TAG=1.0.0

UV_TAG=0.9.10
JDK_TAG=21

# MIRRORS_URL=mirrors.tencentyun.com
MIRRORS_URL="mirrors.ustc.edu.cn"
CLEAN_APT_CACHE=1


# 镜像列表（格式：镜像名:标签）
IMAGES=(
  # "openjdk:${JDK_TAG}"
  "ubuntu:24.04"
  "ghcr.io/astral-sh/uv:${UV_TAG}"
  "postgres:16"
  "redis:8"
)

for IMAGE in "${IMAGES[@]}"; do
  NAME=$(echo "${IMAGE}" | cut -d: -f1)
  TAG=$(echo "${IMAGE}" | cut -d: -f2-)
  if ! docker images | grep -q "^${NAME}[[:space:]]\+${TAG}[[:space:]]"; then
    echo "pull image: ${IMAGE}"
    docker pull "${IMAGE}" || {
      echo "failed to pull image ${IMAGE}, aborting!";
      exit 1;
    }
  else
    echo "found ${IMAGE}, skip docker pull."
  fi
done

docker build -t hello-java:${IMAGE_TAG} -f dockerfiles/Dockerfile \
  --build-arg UV_TAG=${UV_TAG} \
  --build-arg JDK_TAG=${JDK_TAG} \
  --build-arg MIRRORS_URL=${MIRRORS_URL} \
  --build-arg CLEAN_APT_CACHE=${CLEAN_APT_CACHE} \
  --no-cache .