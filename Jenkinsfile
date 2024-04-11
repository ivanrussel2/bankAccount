pipeline {
    agent any
    stages {
     stage('test') {

                steps{
                     echo 'testing the job'
                     echo 'Executing test for $BRANCH_NAME'
                     }
            }
        stage('build') {
         when{
                expression {
                    BRANCH_NAME == 'master'
                }
             }
            steps{
                echo 'building the job'
            }
        }

        stage('deploy') {
            steps{
              echo 'deploying the job'
              }
        }
    }
}