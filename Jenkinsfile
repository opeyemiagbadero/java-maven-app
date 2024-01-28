pipeline {
    agent any

    stages {
        stage("copy files to ansible server") {
            steps {
                script {
                    echo "copy all neccesary files to ansible control node"
                    sshagent(['ansible-server-key']) {
                        sh "sudo scp -o StrictHostKeyChecking=no ansible/* ubuntu@3.8.22.26:/ubuntu/"

                        withCredentials([sshUserPrivateKey(credentialsId: "ec2-server-key", keyFileVariable: 'keyfile', usernameVariable: 'user' )]) {
                            sh "sudo scp ${keyfile} ubuntu@3.8.22.26:/ubuntu/docker-server.pem"
                        }
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