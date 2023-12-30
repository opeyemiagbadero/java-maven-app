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
            steps {
                script {
                    echo 'Deploying docker image ...'
                    
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