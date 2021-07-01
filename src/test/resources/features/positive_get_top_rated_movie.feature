@smoke
Feature:Get the top rated movies on TMDb.

  @positive_top_rated
  Scenario Outline: Get the top rated movies list on TMDb with specific parameters
    Given check The API with valid "api_key" and the API should be available
    When user send request to endpoint "/movie/top_rated" with valid "api_key" and following query params
      | language | <language> |
      | page     | <number>   |
    Then the response code should be 200 and page number should be <number>
    Examples:
      | language | number |
      | en_US    | 112    |
      | en_UK    | 22     |
      | nl_NL    | 13     |
      | tr_TR    | 333    |