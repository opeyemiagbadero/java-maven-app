#!/usr/bin/env groovy

pipeline {
    agent any
    stages {
        
        stage('test') {
            steps {
                script {
                    echo "testing the application..."
                }
            
            }
        }
        
        stage('build') {
            steps {
                script {
                    echo "building the application..."
                }
            }
        }
        
        stage('Deploy') {
            steps {
                script {
                    def dockerCmd = 'docker run -p 3080:3080 -d opeyemiagbadero/demo-app:1.0'
                    sshagent(['ec2-server-key']) {
                         sh "ssh -o StrictHostKeyChecking=no ec2-user@3.8.171.41 ${dockerCmd}"
                    }
                }
                
            }
        }
    }
}
