#!/usr/binn/env groovy

@Library('jenkins-shared-library')
def gv

pipeline {
    agent any
    tools {
        maven 'maven-3.6'
    }
    stages {
        stage ("init") {
            steps {
                // groovy script initialiation
                script {
                    gv = load "script.groovy"
                }
            }
        }
        stage('build jar') {
            steps {
                // Your build steps go here
                script {
                    gv.buildJar()  
                }
            }
        }

        stage('build image') {
    steps {
        script {                     
           gv.buildImage()
        }
    }
}

        stage('Deploy') {
            steps {
                // Your deployment steps go here
                script {
                    gv.deployApp()
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
