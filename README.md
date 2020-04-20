# OOP Assignment: Machine Learning Model using Naive Bayes

### How it works

The model is trained using a table in comma delimited csv file. The first row is assumed to be the dependent features, the last column is assumed to be the class variable and the rest is assumed to be the responses. In order to gather the relevant data for the Naive Bayes classifier, a count is added for each outcome and for each unique combination of a feature and outcome.

After reading the data, the GUI will show the features and the possible responses for each one in a combo box. Here you can select the symptoms you wish to use to make a prediction. Click predict and the chance of having COVID-19 given the symptoms entered will be calculated and displayed.

The model can be trained using more data sets, adding to all previous data. If more features or responses are included in a new data set, they will be shown in the GUI along with the previous features and responses. 

### Possible improvements

If I had more time to work on this I would aim to make it more dynamic, allowing it to be trained with other forms of data sets (excel, xml, databases). I would also let the user confirm that the features and outcomes have been identified correctly rather than assuming the layout of the data.

### Classes

#### Main

This class contains the main method, the program starts here. The GUI is instantiated here.

#### Data

The Data class reads data sets. The count of each outcome is recorded here, and an instance of the Symptom class is created for each feature in the data set.

#### Symptom

An instance is created for each dependent feature, each unique feature and response is a key to a hashmap where the count is stored.

#### NaiveBayes

This class contains static methods for calculating the probability.

#### MainMenu

This class creates the GUI which controls the whole program.