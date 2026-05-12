pipeline {
    agent any
    tools {
        maven 'Mavn-3.9'
    }
    environment {
        PATH = "/opt/homebrew/bin:/usr/local/bin:${env.PATH}"
        DOCKER_HUB_CREDENTIALS = credentials('leoliyanmin')
        DOCKER_IMAGE = 'leoliyanmin/teedy'
        DOCKER_TAG = "${env.BUILD_NUMBER}"
    }
    stages {
        stage('Maven Build') {
            steps {
                sh 'mvn -Pprod -DskipTests clean install'
            }
        }
        stage('Build Docker Image') {
            steps {
                sh 'docker build -t ${DOCKER_IMAGE}:${DOCKER_TAG} -t ${DOCKER_IMAGE}:latest .'
            }
        }
        stage('Push to Docker Hub') {
            steps {
                sh """
                    echo "\$DOCKER_HUB_CREDENTIALS_PSW" | docker login -u "\$DOCKER_HUB_CREDENTIALS" --password-stdin
                    docker push ${DOCKER_IMAGE}:${DOCKER_TAG}
                    docker push ${DOCKER_IMAGE}:latest
                """
            }
        }
        stage('Run Three Containers') {
            steps {
                sh """
                    docker stop teedy-8082 teedy-8083 teedy-8084 || true
                    docker rm teedy-8082 teedy-8083 teedy-8084 || true
                    docker run -d --name teedy-8082 -p 8082:8080 ${DOCKER_IMAGE}:${DOCKER_TAG}
                    docker run -d --name teedy-8083 -p 8083:8080 ${DOCKER_IMAGE}:${DOCKER_TAG}
                    docker run -d --name teedy-8084 -p 8084:8080 ${DOCKER_IMAGE}:${DOCKER_TAG}
                """
            }
        }
    }
    post {
        always {
            sh 'docker ps --filter "name=teedy"'
        }
    }
}
