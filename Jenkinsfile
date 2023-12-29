pipeline {
    agent any

    stages {
        stage('Build app') {
            steps {
                script {
                    echo 'Building the application...'
                }                              
            }
        }

        stage('build image') {
            steps {
                script {
                    echo 'Building the docker image'
                }                               
            }
        }

        stage('Deploy') {
            environment {
                AWS_ACCESS_KEY_ID = credentials ('jenkins_aws_access_key_id')
                AWS_SECRET_ACCESS_KEY =credentials ('jenkins_aws_secret_access_key')
            }
            steps {
                script {
                    echo 'Deploying docker image ...'
                    sh 'kubectl create deployment nginx-deployment --image=redis'
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