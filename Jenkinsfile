pipeline {

    agent any

    tools {
        maven 'Maven'
    }

    stages {

        stage('Clone Repository') {

            steps {
                git 'YOUR_GITHUB_REPOSITORY_URL'
            }
        }

        stage('Build Project') {

            steps {
                sh 'mvn clean compile'
            }
        }

        stage('Run Selenium Tests') {

            steps {
                sh 'mvn test'
            }
        }

        stage('Run Postman Tests') {

            steps {
                sh 'newman run postman/ReqRes_Login_Collection.json'
            }
        }
    }

    post {

        always {

            junit 'target/surefire-reports/*.xml'

            archiveArtifacts artifacts: 'target/**/*.*', allowEmptyArchive: true
        }

        success {
            echo 'Pipeline executed successfully!'
        }

        failure {
            echo 'Pipeline failed!'
        }
    }
}
