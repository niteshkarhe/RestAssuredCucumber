@SuiteTearDown
Feature: This feature file has all Candidates api request

@datatable
Scenario: Example of datatable
Given the following animals:
| cow |
| horse |
| sheep |

@datatabletype
Scenario: Example of DataTableType
Given these are my favourite authors
|firstName | lastName | book |
|Nitish    | Karhe    | Maths |
|Amita     | Chede    | Physics |

@parametertype
Scenario: Example of ParameterType
Given My favorite author is Nitesh

@docstringtype
Scenario: Example of DocStringType
Given some more information
  """json
  [
      { 
         "firstName": "Nitish",
         "lastName": "Karhe", 
         "book": "Maths"
      },
      {
         "firstName": "Amita",
         "lastName": "Chede", 
         "book": "Physics"
      }
  ]
  """
