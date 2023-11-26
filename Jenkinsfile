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

        stage('build image') {
    steps {
        script {
            echo 'Building the docker image ...'
            
            withCredentials([usernamePassword(credentialsId:'docker-hub-repo', passwordVariable:'PASSWORD', usernameVariable: 'USERNAME')]) {
                sh """
                    docker build -t opeyemiagbadero/demo-app:jma-2.0 .
                    echo \${PASSWORD} | docker login -u \${USERNAME} --password-stdin
                    docker push opeyemiagbadero/demo-app:jma-2.0
                """
            }
        }
    }
}

        stage('Deploy') {
            steps {
                // Your deployment steps go here
                echo 'Deploying the application...'
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
