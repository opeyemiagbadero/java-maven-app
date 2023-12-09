#!/usr/bin/env groovy

@Library('jenkins-shared-library') _

def gv

pipeline {
    agent any
    tools {
        maven 'maven-3.6'
    }
    environment {
        IMAGE_NAME = 'opeyemiagbadero/demo-app:jma-5.0'
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
                    echo 'Deploying Docker image to EC2...'
                    def dockerCmd = "docker run -p 8080:8080 -d ${IMAGE_NAME}"
                    sshagent(['ec2-server-key']) {
                        sh "ssh -o StrictHostKeyChecking=no ec2-user@3.8.171.41 ${dockerCmd}"
                    }
                }
            }
        }
    }
}