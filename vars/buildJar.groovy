//indicate that this is the groovy file
#!/usr/bin/env groovy

def call() {
    echo "building the application"
    sh 'mvn package'
}