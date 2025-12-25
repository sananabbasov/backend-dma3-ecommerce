# Backend Deployment Documentation

## Getting Started

This document describes how to deploy a **Spring Boot backend application** on an **Ubuntu Droplet** using:

* Java 17
* PostgreSQL
* GitHub Actions (Self-Hosted Runner)
* systemd
* Nginx
* SSL (Let’s Encrypt)

---

## System Requirements

* Ubuntu 20.04+
* Root access
* Domain name (example: `backendlesson.store`)
* GitHub repository
* Spring Boot application

---

## System Update

```bash
sudo apt update
sudo apt upgrade -y
```

---

## Swap Memory Setup (4GB)

Used to prevent memory issues on low-RAM droplets.

```bash
sudo fallocate -l 4G /swapfile
sudo chmod 600 /swapfile
sudo mkswap /swapfile
sudo swapon /swapfile
```

---

## Create Backend User

```bash
adduser backend
usermod -aG sudo backend
```

---

## Java & Maven Installation

```bash
sudo apt install openjdk-17-jre-headless -y
sudo apt install maven -y
```

Check versions:

```bash
java -version
mvn -version
```

---

## PostgreSQL Installation & Configuration

```bash
sudo apt install postgresql postgresql-contrib -y
sudo systemctl start postgresql.service
```

Switch to postgres user:

```bash
sudo -i -u postgres
psql
```

Create database:

```sql
CREATE DATABASE database_name;
```

Exit:

```sql
\q
exit
```

Set postgres password:

```sql
ALTER USER postgres WITH PASSWORD '123456';
```

---

## GitHub Actions – Self Hosted Runner

```bash
sudo ./svc.sh install
sudo ./svc.sh start
sudo ./svc.sh status
```

---

## Systemd Service Configuration

Create service file:

```bash
sudo nano /etc/systemd/system/backend.service
```

```ini
[Unit]
Description=Student CRM Spring Boot Application
After=syslog.target network.target

[Service]
User=backend
WorkingDirectory=/home/backend/actions-runner/_work/dma-4-anime/dma-4-anime/target
ExecStart=/usr/bin/java -jar anime-0.0.1-SNAPSHOT.jar
SuccessExitStatus=143
Restart=always
RestartSec=10

[Install]
WantedBy=multi-user.target
```

Enable and manage service:

```bash
sudo systemctl enable backend.service
sudo systemctl start backend.service
sudo systemctl restart backend.service
sudo systemctl status backend.service
```

---

## Sudo Permission for GitHub Actions

Allow service restart without password.

```bash
sudo visudo
```

Add:

```bash
backend ALL=(ALL) NOPASSWD: /bin/systemctl restart backend.service
```

---

## GitHub Actions Workflow

`.github/workflows/ci.yml`

```yaml
name: CI

on:
  push:
    branches: [ "main" ]

jobs:
  build:
    runs-on: self-hosted

    steps:
      - name: Checkout repository
        uses: actions/checkout@v2

      - name: Set up JDK 17
        uses: actions/setup-java@v2
        with:
          distribution: 'adopt'
          java-version: '17'

      - name: Build with Maven
        run: |
          mvn clean install -DskipTests --batch-mode --errors --fail-at-end

      - name: Restart Backend
        run: sudo systemctl restart backend.service
```

---

## Nginx Installation

```bash
sudo apt install nginx -y
```

---

## HTTP to HTTPS Redirect

```nginx
server {
    listen 80;
    server_name backendlesson.store www.backendlesson.store;

    return 301 https://$host$request_uri;
}
```

---

## SSL Installation (Certbot)

```bash
sudo apt install certbot python3-certbot-nginx -y
sudo certbot --nginx -d backendlesson.store -d www.backendlesson.store
```

---

## HTTPS Reverse Proxy Configuration

```nginx
server {
    listen 443 ssl;
    server_name backendlesson.store www.backendlesson.store;

    ssl_certificate /etc/letsencrypt/live/backendlesson.store/fullchain.pem;
    ssl_certificate_key /etc/letsencrypt/live/backendlesson.store/privkey.pem;

    ssl_protocols TLSv1.2 TLSv1.3;
    ssl_ciphers HIGH:!aNULL:!MD5;

    location / {
        proxy_pass http://localhost:8080;
        proxy_http_version 1.1;

        proxy_set_header Upgrade $http_upgrade;
        proxy_set_header Connection keep-alive;
        proxy_set_header Host $host;

        proxy_set_header X-Forwarded-For $proxy_add_x_forwarded_for;
        proxy_set_header X-Forwarded-Proto $scheme;
    }
}
```

Restart Nginx:

```bash
sudo systemctl restart nginx
```

---

## Deployment Flow

1. Code pushed to `main`
2. GitHub Actions triggered
3. Maven build executed
4. systemd service restarted
5. Application live via HTTPS

---

## Reference Documentation

* [Apache Maven Documentation](https://maven.apache.org/guides/index.html)
* [Spring Boot Maven Plugin](https://docs.spring.io/spring-boot/4.0.0/maven-plugin)
* [Spring Web](https://docs.spring.io/spring-boot/4.0.0/reference/web/servlet.html)
* [Spring Data JPA](https://docs.spring.io/spring-boot/4.0.0/reference/data/sql.html)

---

## Guides

* [Building a RESTful Web Service](https://spring.io/guides/gs/rest-service/)
* [Serving Web Content with Spring MVC](https://spring.io/guides/gs/serving-web-content/)
* [Accessing Data with JPA](https://spring.io/guides/gs/accessing-data-jpa/)

