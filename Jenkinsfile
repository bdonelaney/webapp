pipeline {
    agent {
        kubernetes {
            label 'testagent1'
            defaultContainer 'jnlp-agent'
        }
    }
    environment {
        ARTIFACTORY     = credentials('artifactory')
    }
    stages {
        stage('JAR Build') {
            steps {
                sh 'hostname'
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
                sh "docker login etlabvlldvopap2.et.lab:80 -u \$ARTIFACTORY_USR -p \$ARTIFACTORY_PSW"
                sh "docker build . -t etlabvlldvopap2.et.lab:80/docker/arunatest:\$(git rev-parse --short HEAD)"
                sh "docker build . -t etlabvlldvopap2.et.lab:80/docker/arunatest:testcandidate"
                sh "docker push etlabvlldvopap2.et.lab:80/docker/arunatest:testcandidate"
                sh "docker push etlabvlldvopap2.et.lab:80/docker/arunatest:\$(git rev-parse --short HEAD)"
            }
        }
        stage('Functional Test') {
            steps {
                // spin up selenium chrome standalone container in same pod as jnlp-agent, in jnlp-agent clone down
                // cucumber test suite and execute
                // git url: "https://github.com/bdonelaney/automation.git"
                sh "echo success"
            }
        }
        stage('Docker Tag Latest') {
            steps {
                //sh "docker tag etlabvlldvopap2.et.lab:80/docker/arunatest:\$(git rev-parse --short HEAD) etlabvlldvopap2.et.lab:80/docker/arunatest:latest"
                //sh "docker push etlabvlldvopap2.et.lab:80/docker/arunatest:latest"
                sh "echo success"
            }
        }
    }
}