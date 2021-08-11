pipeline {
    agent any
    stages {
        stage('Clean') {
            steps {
                sh './gradlew clean'
            }
        }
        stage ('Ktlint check'){
                steps {
                    sh './gradlew ktlintCheck'
                }
        }
        stage('Compile') {
            steps {
                sh './gradlew compileDevDebugKotlin'
            }
        }
        stage('Unit test') {
            steps {
                sh './gradlew test'
            }
        }
        stage('Build') {
            steps {
                sh './gradlew build'
            }
        }
        stage ('Distribute Dev') {
            when {
                branch 'master'
            }
            steps {
                sh "./gradlew assembleDev appDistributionUploadDevDebug"
            }
        }
        stage ('Distribute Prod') {
                    when {
                        branch 'master'
                        tag "*"
                    }
                    steps {
                        sh "./gradlew assembleRelease appDistributionUploadProdRelease"
                    }
                }
    }
    post {
            always {
                cleanWs()
            }
        }
}