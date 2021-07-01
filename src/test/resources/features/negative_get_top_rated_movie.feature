@smoke
Feature:Get the top rated movies on TMDb.

  @negative_top_rated
  Scenario Outline: Get the top rated movies list on TMDb with invalid parameters
    When user send request to endpoint "/movie/top_rated" with invalid "api_key" and following query params
      | language | <language> |
      | page     | <number>   |
    Then the response code should be 401
    And the response "status_message" should be "Invalid API key: You must be granted a valid key."
    Examples:
      | language | number |
      | en_US    | 112    |
      | en_UK    | 22     |
      | nl_NL    | 13     |
      | tr_TR    | 333    |