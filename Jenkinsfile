pipeline {
    agent any
    tools {
        maven 'maven-3.6'
    }
    stages {
        stage('build jar') {
            steps {
                // Your build steps go here
                script {
                    echo 'Building the application...'
                    sh 'mvn package'
                }
            }
        }

    stages {
        stage('build image') {
            steps {
                // Your build steps go here
                script {
                    echo 'Building the docker image ...'
                    withCredentials([usernamePassword(credentialsId:'docker-hub-repo', passwordVariable:'PASSWORD', usernameVariable: 'USERNAME')])
                    sh 'docker build -t opeyemiagbadero/demo-app:jma-2.0 .'
                    sh "echo $PASSWORD | docker login -u $USERNAME --password-stdin"
                    sh 'docker push opeyemiagbadero/demo-app:jma-2.0'
                }
            }
        }    
        
        stage('Deploy') {
            steps {
                // Your deployment steps go here
          stage('Test') {
            steps {
                // Your test steps go here
                echo 'Testing...'
            }
        }      echo 'Deploying the application...'
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
}