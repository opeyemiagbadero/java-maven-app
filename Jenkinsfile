def gv

pipeline {
    agent any
    parameters {
        choice(name: 'VERSION', choices: ['1.1.0', '1.2.0', '1.3.0'], description: '')
        booleanParam(name: 'executeTests', defaultValue: true, description: '')
    }
    stages {
        stage('Init') {
            steps {
                // Your init steps go here
                script {
                    gv = load "script.groovy"
                }
            }
        }
        stage('Build') {
            steps {
                // Your build steps go here
                script {
                    gv.buildApp()
                }
            }
        }
        stage('Test') {
            when {
                expression {
                    params.executeTests
                }
            }
            steps {
                // Your test steps go here
                script {
                    gv.testApp()
                }
            }
        }
        stage('Deploy') {
           
            steps {
                // Your deployment steps go here
                script {
                    env.ENV = input message: "Select the environment to deploy to", ok: "Done", parameters: [choice(name: 'ONE', choices: ['dev', 'staging', 'prod'], description: '')]
                    gv.deployApp()
                    echo "Deploying to ${ENV}"
                    
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
