# Verify - Indonesian Fact-Check Android App
[![<B21-CAP0133>](https://circleci.com/gh/B21-CAP0133/verify-android-app.svg?style=svg)](https://circleci.com/gh/B21-CAP0133/verify-android-app)
  
# Overview
Verify is an app that enables user to check wheter an article or news headline is a hoax or not. The app will predict whether it is a hoax or not,
and gives user the appropiate information if exists.
  
# Android
Connects to the API cloud server and passes user input to the cloud and ultimately to the inference engine, and retrieves its output as a JSON object 
and displays it for user in an easy to use, clean chat interface.

# Docker/Cloud
Python scripts to do content searching based on cosine similarity, and to access deployed ML model via Google AI Platform
Dockerize and built into a flask app, working in Google Cloud Run
Scripts can be found in backend folder.

# Machine Learning
Analyzes user's input to determine if its hoax or not, graded in percentage and attempts a search in our hoax clarification
database. If it exists, we will pass it to the output along with the prediction result.
