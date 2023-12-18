#!/usr/bin/env groovy

@Library('jenkins-shared-library') _

def gv

pipeline {
    agent any
    tools {
        maven 'maven-3.6'
    }
    
    stages {
        stage('increment version') {
            steps {
                script {
                    echo 'incrementing the app version...'
                    sh 'mvn build-helper:parse-version versions:set ' +
                       '-DnewVersion=\\\${parsedVersion.majorVersion}.\\\${parsedVersion.minorVersion}.\\\${parsedVersion.nextIncrementalVersion} ' +
                       'versions:commit'
                    def matcher = readFile('pom.xml') =~ '<version>(.+)</version>'
                    def version = matcher[0][1]
                    env.IMAGE_NAME = "opeyemiagbadero/demo-app:$version-$BUILD_NUMBER"
                }
            }
        }
        stage('Build App') {
            steps {                
                script {
                    echo 'Building application jar....'
                    buildJar()
                }
            }
        }

        stage('Build Image') {
            steps {
                script {
                    echo 'building Docker image ....'
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
                    def ec2Instance = "ec2-user@18.134.243.243"
                    sshagent(['ec2-server-key']) {
                        sh "scp server-cmds.sh ${ec2Instance}:/home/ec2-user"
                        sh "scp docker-compose.yaml ${ec2Instance}:/home/ec2-user"
                        sh "ssh -o StrictHostKeyChecking=no ${ec2Instance} ${shellCmd}"
                    }
                }
            }
        }

        stage('commit version update') {
            steps {
                script {
                    withCredentials([sshUserPrivateKey(credentialsId: '2busola-jenkins', keyFileVariable: 'ssh_key_credential')]) {
                        sh 'git config --global user.email "jenkins@example.com"'
                        sh 'git config --global user.name "jenkins"'

                        sh 'git status'
                        sh 'git branch'
                        
                        sh 'git remote set-url origin git@github.com:opeyemiagbadero/java-maven-app.git'
                        sh 'git add .'
                        sh 'git commit -m "ci:version bump-confirm"'
                        sh 'git push origin HEAD:EC2-jenkinspipeline1'
                        
                    }
                }
            }
        }





    }
}