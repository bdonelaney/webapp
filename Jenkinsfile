pipeline {
    agent {
        kubernetes {
            label 'testagent1'
            defaultContainer 'maven'
            yaml """
apiVersion: v1
kind: Pod
metadata:
labels:
  component: ci
spec:
  # Use service account that can deploy to all namespaces
  serviceAccountName: cd-jenkins
  containers:
  - name: maven
    image: etlabvlldvopap2.et.lab:80/docker/mavenjnlp:latest
    command:
    - cat
    tty: true
    volumeMounts:
        - mountPath: /var/run/docker.sock
          name: docker-sock
  volumes:
    - name: docker-sock
      hostPath:
        path: /var/run/docker.sock
"""
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
                sh "docker build . -t etlabvlldvopap2.et.lab:80/docker/arunatest:\$(git rev-parse --short HEAD)"
                sh "docker push etlabvlldvopap2.et.lab:80/docker/arunatest:\$(git rev-parse --short HEAD)"
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
                sh "docker tag etlabvlldvopap2.et.lab:80/docker/arunatest:\$(git rev-parse --short HEAD) etlabvlldvopap2.et.lab:80/docker/arunatest:latest"
                sh "docker push etlabvlldvopap2.et.lab:80/docker/arunatest:latest"
            }
        }
    }
}