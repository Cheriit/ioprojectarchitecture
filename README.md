# Scenario Quality Checker project for Software Engineering ![Build status](https://travis-ci.com/Cheriit/ioprojectarchitecture.svg?branch=main)


Scenario Quality Checker project for Software Engineering classes at Poznan University of Technology.

Javadoc is available [here](https://cheriit.github.io/ioprojectarchitecture/)

## Team
- [Filip Szóstak](https://github.com/Cheriit) - ScrumMaster
- [Wiktor Górczak](https://github.com/wiktorgorczak) - Proxy PO
- [Piotr Góreczny](https://github.com/ajana4096)


## Project goal
Application for analying use case scenarios offering REST interface

## Endpoints:

**getAllScenarios**
----
Returns json data about all scenarios.

* **URL**

/

* **Method:**

`GET`
  
*  **URL Params**

 None

* **Data Params**

  None

* **Success Response:**

  * **Code:** 200 <br />
**Content:** `List<MainScenario>
 
* **Error Response:**

  * **Code:** 404 NOT FOUND <br />
**Content:** None
**getScenario**
----
  Returns json data about a scenario by id.

* **URL**

  /:id

* **Method:**

  `GET`
  
*  **URL Params**

  **Required:**
 
   `id=[String]`

* **Data Params**

  None

* **Success Response:**

  * **Code:** 200 <br />
**Content:** MainScenario
 
* **Error Response:**

  * **Code:** 404 NOT FOUND <br />
**Content:** `None

**updateScenario**
----
  updates a scenario by id.

* **URL**

  /:id

* **Method:**

  `PUT`
  
*  **URL Params**

  **Required:**
 
   `id=[String]`

* **Data Params**

  None

* **Success Response:**

  * **Code:** 200 <br />
**Content:** "Scanerio updated."
 
* **Error Response:**

  * **Code:** 404 NOT FOUND <br />
**Content:** `None

**deleteScenario**
----
  deletes scenario by id.

* **URL**

  /:name

* **Method:**

  `DELETE`
  
*  **URL Params**

  **Required:**
 
   `id=[String]`

* **Data Params**

  None

* **Success Response:**

  * **Code:** 200 <br />
**Content:** None
 
* **Error Response:**

  * **Code:** 404 NOT FOUND <br />
**Content:** `None

**createScenerio**
----
  inserts given scenario to the system

* **URL**

  /

* **Method:**

  `POST`
  
*  **URL Params**

None

* **Data Params**

**Required:**
 
   `scenario = [MainScenario]`

* **Success Response:**

  * **Code:** 200 <br />
**Content:** None
 
* **Error Response:**

  * **Code:** 404 NOT FOUND <br />
**Content:** `None

**getScenarioStepCount**
----
  Returns number of steps in the scenario

* **URL**

  /:id/stepcount

* **Method:**

  `GET`
  
*  **URL Params**

  **Required:**
 
   `id=[String]`

* **Data Params**

  None

* **Success Response:**

  * **Code:** 200 <br />
**Content:** scenarioStepCount = [int]
 
* **Error Response:**

  * **Code:** 404 NOT FOUND <br />
**Content:** `None

**getScenarioLimitedByDepth**
----
  Returns scenario limited to nodes up to a given depth from mainscenario

* **URL**

  /:id/getScenarioLimitedByDepth/:depth

* **Method:**

  `GET`
  
*  **URL Params**

  **Required:**
 
   `id=[String]`
   `depth=[int]`

* **Data Params**

  None

* **Success Response:**

  * **Code:** 200 <br />
**Content:** MainScenario
 
* **Error Response:**

  * **Code:** 404 NOT FOUND <br />
**Content:** `None
