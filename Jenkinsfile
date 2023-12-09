#!/usr/bin/env groovy

@Library('jenkins-shared-library') _

def gv

pipeline {
    agent any
    tools {
        maven 'maven-3.6'
    }
    environment {
        IMAGE_NAME = 'opeyemiagbadero/demo-app:jma-10.0'
    }
    stages {
        stage('Build App') {
            steps {
                // Your build steps go here
                script {
                    echo 'Building application jar....'
                    buildJar()
                }
            }
        }

        stage('Build Image') {
            steps {
                script {
                    echo 'building Docker image ...'
                    buildImage(env.IMAGE_NAME)
                    dockerLogin()
                    dockerPush(env.IMAGE_NAME)
                }
            }
        }

        stage('Deploy') {
            steps {
                script {
                    echo 'deploying Docker image to EC2...'
                    def shellCmd = "bash ./server-cmds.sh ${IMAGE_NAME}"
                    def ec2Instance = "ec2-user@3.8.171.41"
                    sshagent(['ec2-server-key']) {
                        sh "scp server-cmds.sh ${ec2Instance}:/home/ec2-user"
                        sh "scp docker-compose.yaml ${ec2Instance}:/home/ec2-user"
                        sh "ssh -o StrictHostKeyChecking=no ${ec2Instance} ${shellCmd}"
                    }
                }
            }
        }
    }
}