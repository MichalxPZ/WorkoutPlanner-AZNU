#!/bin/zsh

# Lista projektów
#projects=("WorkoutIntegration" "WorkoutPlannerRestClient" "WorkoutPlannerRestService" "WorkoutPlannerSOAP" "WorkoutPlannerSoapClient" "WorkoutUI")
#projects=("WorkoutPlannerSOAPClient")
projects=("WorkoutIntegration")
#projects=("WorkoutUI")
#projects=("WorkoutPlannerRestService")
#projects=("WorkoutPlannerRestClient")
# Przejdź przez każdy projekt
for projectDir in "${projects[@]}"; do
    echo "Building Docker image for project: $projectDir"

    # Przejdź do katalogu projektu
    cd "$projectDir" || { echo "Directory $projectDir not found! Skipping..."; continue; }

    # Buduj obraz Dockera za pomocą Maven
    ./mvnw spring-boot:build-image \
        -D"spring-boot.build-image.imageName=aznu-workout/${projectDir:l}" \
        -DskipTests

    # Powrót do katalogu głównego
    cd ..
done