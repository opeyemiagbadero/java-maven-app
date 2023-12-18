#!/usr/bin/env groovy

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
                    env.IMAGE_NAME = "${version}-${BUILD_NUMBER}"
                }
            }
        }

        stage('Build app') {
            steps {
                script {
                    echo 'Building the application...'
                    sh 'mvn clean package'
                }
            }
        }

        stage('Build image') {
            steps {
                script {
                    echo 'Building the Docker image...'
                    withCredentials([usernamePassword(credentialsId: 'docker-hub-repo', passwordVariable: 'PASSWORD', usernameVariable: 'USERNAME')]) {
                        sh """
                            docker build -t opeyemiagbadero/demo-app:${IMAGE_NAME} .
                            echo \${PASSWORD} | docker login -u \${USERNAME} --password-stdin
                            docker push opeyemiagbadero/demo-app:${IMAGE_NAME}
                        """
                    }
                }
            }
        }

        stage('Deploy') {
            steps {
                script {
                    echo 'deploying the docker image to EC2...'
                    // Your deployment steps go here
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
                        sh 'git config --list'

                        sh 'git remote set-url origin git@github.com:opeyemiagbadero/java-maven-app.git'
                        sh 'git add .'
                        sh 'git commit -m "ci:version bump-confirm"'
                        sh 'git push origin HEAD:versioning-jenkins'
                        
                    }
                }
            }
        }
        
    }

    post {
        success {
            echo 'Pipeline succeeded! Send notifications, etc.'
        }
        failure {
            echo 'Pipeline failed! Send notifications, etc.'
        }
    }
}




