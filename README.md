## Description:
This app connects to the StarWars API (https://swapi.co/) and requests information about StarWars Universe. 

## Workflow:
	•	The user opens the app and lands on the character search screen where they can enter the name and find the characters of star wars. 
	•	When the character is found they can click on it which will land them on character details screen which has all details like language, homeworld, planet, species and films. 

## Architecture:
App follows MVVM - Model -View -ViewModel architecture. This patter basically has a view model which interacts with the model and prepares observables which can be observed by a view for the changes in data and change the view accordingly.
This architecture helps in separation of concerns without creating hard dependencies between view and view model

## 3rd party libraries:
	•	Dagger2 (https://google.github.io/dagger/) is used for dependency injection. 
	•	Retrofit (https://square.github.io/retrofit/) is used for making internet requests. 
	•	RxJava2  is used to make the application run smooth. 

## Few highlights:
	•	Retrofit caching is implemented to getting faster response if the queries are repeated and there is no change in network data 
	•	Also caching helps to view data without network which gives a seamless experience if you are transition between networks and also gives a fast experience for the cached queries in slow network 
	•	UI has been kept simple and user friendly. 

## Tests:
	•	Mockito and espresso is used for testing 
	•	All the test cases can be found in project 
