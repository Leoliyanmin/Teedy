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
                script {
                    docker.build("${env.DOCKER_IMAGE}:${env.DOCKER_TAG}")
                }
            }
        }
        stage('Push to Docker Hub') {
            steps {
                script {
                    docker.withRegistry('https://registry.hub.docker.com', 'DOCKER_HUB_CREDENTIALS') {
                        docker.image("${env.DOCKER_IMAGE}:${env.DOCKER_TAG}").push()
                        docker.image("${env.DOCKER_IMAGE}:${env.DOCKER_TAG}").push('latest')
                    }
                }
            }
        }
        stage('Run Three Containers') {
            steps {
                script {
                    sh 'docker stop teedy-8082 teedy-8083 teedy-8084 || true'
                    sh 'docker rm teedy-8082 teedy-8083 teedy-8084 || true'
                    docker.image("${env.DOCKER_IMAGE}:${env.DOCKER_TAG}").run('--name teedy-8082 -d -p 8082:8080')
                    docker.image("${env.DOCKER_IMAGE}:${env.DOCKER_TAG}").run('--name teedy-8083 -d -p 8083:8080')
                    docker.image("${env.DOCKER_IMAGE}:${env.DOCKER_TAG}").run('--name teedy-8084 -d -p 8084:8080')
                }
            }
        }
    }
    post {
        always {
            sh 'docker ps --filter "name=teedy"'
        }
    }
}
