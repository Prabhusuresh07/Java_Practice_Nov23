# Java 21 and Maven 3.10 environment
FROM maven:3.10-eclipse-temurin-21-noble

# Install Google Chrome
RUN apt-get update && \
    apt-get install -y wget gnupg ca-certificates && \
    mkdir -p /etc/apt/keyrings && \
    wget -qO- https://dl.google.com/linux/linux_signing_key.pub | \
    gpg --dearmor -o /etc/apt/keyrings/google-chrome.gpg && \
    echo "deb [arch=amd64 signed-by=/etc/apt/keyrings/google-chrome.gpg] https://dl.google.com/linux/chrome/deb/ stable main" \
    > /etc/apt/sources.list.d/google-chrome.list && \
    apt-get update && \
    apt-get install -y google-chrome-stable && \
    rm -rf /var/lib/apt/lists/*

# Set working directory
WORKDIR /app

# Copy project files
COPY . .

# Execute TestNG automation tests in headless mode
CMD ["mvn", "clean", "test", "-Ddocker=true"]