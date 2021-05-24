# MyVIB-VirtualAssistant

## Description
![MyVIB Assistant Advertisement](https://vib-competition-bucket-resource.s3-ap-southeast-2.amazonaws.com/ADVERT.png)
A virtual assistant application implemented for VIB hackathon competition

## App icon
![MyVIB Assistant Icon](https://vib-competition-bucket-resource.s3-ap-southeast-2.amazonaws.com/Icon.png)

## Platform
Android

## Plan
- [x] Design mockups
- [x] Design and Implement Backend API
- [x] Design and Implement Android Front-end
- [x] View account balance
- [x] Make payment
- [x] Make transaction
- [x] Customer service (may need more features)
- [ ] Code refactoring (MVVM)

## Technology
- Android Jetpack
- Google's DialogFlow (Intent recognition)
- Google's Speech Recognizer (Speech recognition)
- Google's Places API (Back-end - for finding bank locations)
- Google's Fused Location Prodvider (for getting user's location)
- Android Studio - Java (Front-end)
- Flask Restful - Python (Back-end)

## Backend
The backend of this application is available at https://vib-competion-backend.herokuapp.com/ and containg the folloing endpoints: 
- ```/balance``` for retrieving dummy balance data
- ```/bills``` for retrieving dummy bills data
- ```/predict/en``` for predicting user intent in English (take { sentence } as body)
- ```/predict/vi``` for predicting user intent in Vietnamese (take { sentence } as body)
- ```/chat/vi``` for chatting with AI in Vietnamese (take { sentence } as body)
- ```/chat/eng``` for chatting with AI in English (take { sentence } as body)
- ```/accounts``` for getting a list of dummy account
- ```/locations``` for retrieving bank location closest to the user (take { lat, long } as body)

## Features
The application currently has the folling features

## Homepage and Choosing language
Customers can choose to use English or Vietnamese for the application
![Home page and Language](https://vib-competition-bucket-resource.s3-ap-southeast-2.amazonaws.com/Language.png)

### View current balance (Voice activated)
This function can be used to view the current user's account details (using dummy data only). This feature can be activated by clicking on "Talk" button and saying 
phrases such as "View account" or "Account details"
![Account details](https://vib-competition-bucket-resource.s3-ap-southeast-2.amazonaws.com/Account.png)
  
### Make payment (Voice activate)
This function can be used to help users to handle payment for electicity, water and phone (using mock data only). This feature can be activated by clicking on "Talk" button and saying phrases such as "Make payment" or "Pay for electricity"
![Balance](https://vib-competition-bucket-resource.s3-ap-southeast-2.amazonaws.com/Payment.png)

### Transfer money (Voice activate)
This function can be used to help users to transfer money to another account. This feature can be activated by clicking on "Talk" button and saying phrases such as "Make transaction" or "Transfer money". Customer can also use the Speech-To-Text functionality to input the account details and bank using their voice

![Transfer money](https://vib-competition-bucket-resource.s3-ap-southeast-2.amazonaws.com/Transfer1.png)
![Transfer money](https://vib-competition-bucket-resource.s3-ap-southeast-2.amazonaws.com/Transfer2.png)

### Find nearby banks:
This function can help users to find the nearest banks or atms using the current location of their phone (using GPS or Network provider). By saying "Find all banks near me" in English or Vietnamese to the app, all the relevant bank will be displayed

![View nearby banks](https://vib-competition-bucket-resource.s3-ap-southeast-2.amazonaws.com/FindLocation.png)

### Customer service
By saying "Customer service" or "I need some help" to the assistant, users wil be taken to a Customer Serivce chat session where they can chat to an AI agent for help. Just type in the text box or clicking on the talking button to use your voice.
![Customer service](https://vib-competition-bucket-resource.s3-ap-southeast-2.amazonaws.com/CustomerService.png)

We currently support following operations 
 - How to change password
 - How to contact VIB

## Demo
Advertisement: [To be Added]
Demo video: [!https://www.youtube.com/watch?v=WaZhVN-2JkU]

## How to run
Download the APK from the following link: [!https://vib-competition-bucket-resource.s3-ap-southeast-2.amazonaws.com/app-release.apk] and select "Install anyway" if asked

## Disclaimer
The code written for the application does not adhere to any architecture like MVP or MVVM as the team focus on developing this app as quickly as possbile, and thus the code may not be as clean and maintainable we had hoped it to be. Nonetheless, in the future, we will refactor it using design patterns and MVVM architecture to make it cleaner and more readable.
