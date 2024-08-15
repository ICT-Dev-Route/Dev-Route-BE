#debian 계열의 리눅스를 사용해야만 크롤링 관련 패키지를 다운 받을 수 있음.
FROM --platform=linux/amd64 openjdk:17-jdk-slim

# 필수 패키지 설치
RUN apt-get update && apt-get install -y \
    wget \
    curl \
    unzip \
    xvfb \
    libxi6 \
    libgconf-2-4 \
    libnss3 \
    libxss1 \
    libappindicator1 \
    fonts-liberation \
    libasound2 \
    xdg-utils \
    default-mysql-client \
    gnupg \
    ca-certificates \
    libappindicator3-1 \
    libgbm1 \
    libnspr4 \
    libxshmfence1 \
    libu2f-udev \
    && rm -rf /var/lib/apt/lists/*

# Google Chrome 설치
RUN wget -q -O /tmp/google-chrome-stable_current_amd64.deb https://dl.google.com/linux/direct/google-chrome-stable_current_amd64.deb \
    && apt-get update \
    && apt-get install -y /tmp/google-chrome-stable_current_amd64.deb \
    && rm /tmp/google-chrome-stable_current_amd64.deb \
    && rm -rf /var/lib/apt/lists/*

# ChromeDriver 설치
RUN CHROMEDRIVER_VERSION=$(curl -sS chromedriver.storage.googleapis.com/LATEST_RELEASE) \
    && wget -q -O /tmp/chromedriver.zip "https://chromedriver.storage.googleapis.com/${CHROMEDRIVER_VERSION}/chromedriver_linux64.zip" \
    && unzip /tmp/chromedriver.zip -d /usr/local/bin/ \
    && rm /tmp/chromedriver.zip

# 애플리케이션 디렉토리 생성 및 JAR 파일 복사
RUN mkdir -p /app
ARG JAR_FILE=build/libs/dev-route-0.0.1-SNAPSHOT.jar
COPY ${JAR_FILE} /app/app.jar

# Java 애플리케이션 실행
ENTRYPOINT ["java", "-jar", "/app/app.jar"]
