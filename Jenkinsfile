def app_name = 'arunatest'

pipeline {
    agent {
        kubernetes {
            label 'testagent1'
            defaultContainer 'jnlp-agent'
        }
    }
    stages {
        stage('JAR Build') {
            steps {
                sh 'mvn clean package'
            }
            post {
                always {
                    junit 'target/surefire-reports/*.xml'
                }
            }
        }
        stage('Docker Build') {
            steps {
                GIT_TAG = sh (
                    script: 'git rev-parse --short HEAD',
                    returnStdout: true
                ).trim()
                sh "sudo docker build . -t etlabvlldvopap2.et.lab:80/docker/${app_name}:${GIT_TAG}"
                sh "sudo docker push etlabvlldvopap2.et.lab:80/docker/${app_name}:${GIT_TAG}"
            }
        }
        stage('Functional Test') {
            steps {
                // spin up selenium chrome standalone container in same pod as jnlp-agent, in jnlp-agent clone down
                // cucumber test suite and execute
                git url: "https://github.com/bdonelaney/automatedtest.git"
                sh "ls -la"
                sh "ls -la automatedtest/"
            }
        }
        stage('Docker Tag Latest') {
            steps {
                sh "sudo docker tag etlabvlldvopap2.et.lab:80/docker/${app_name}:${GIT_TAG} etlabvlldvopap2.et.lab:80/docker/${app_name}:latest"
                sh "sudo docker push etlabvlldvopap2.et.lab:80/docker/${app_name}:latest"
            }
        }
    }
}