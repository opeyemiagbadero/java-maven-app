pipeline {
    agent any

    stages {
        stage("copy files to ansible server") {
            steps {
                script {
                    echo "copy all neccesary files to ansible control node"
                    sshagent(['ansible-server-key']) {                        
                        sh "scp -o StrictHostKeyChecking=no ansible/* ubuntu@18.170.112.188:/home/ubuntu"                       

                        withCredentials([sshUserPrivateKey(credentialsId: "ec2-server-key", keyFileVariable: 'keyfile', usernameVariable: 'user')]) {                            
                            sh "scp ${keyfile} ubuntu@18.170.112.188:/home/ubuntu/ssh-key.pem"
                        }
                    }
                }                              
            }
        } 
        // stage("execute ansible playbook") {
        //     steps {
        //         script {
        //             echo "calling ansible playbook to configure ec2 instances"
        //             def remote = [:]
        //             remote.name = "ansible-server"
        //             remote.host = "3.8.22.26"
        //             remote.allowAnyHosts = true
        //             withCredentials([sshUserPrivateKey(credentialsId: "ansible-server-key", keyFileVariable: 'keyfile', usernameVariable: 'user')]) {
        //                 remote.user = user
        //                 remote.identityFile = keyfile
        //                 sshCommmand remote: remote, command: "ls -l"

        //             }
                    
        //         }
        //     }
        // }
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