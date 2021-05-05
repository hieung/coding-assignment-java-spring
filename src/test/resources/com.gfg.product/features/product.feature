Feature: Product API BDDs

  Scenario: Get one product
    Given Rest endpoint is up
    When Get one product by id 1
    Then Returned product is equal product in DB

  Scenario: Find all products
    Given Rest endpoint is up
    When Get all products
    Then Check product number return by five

  Scenario: Create product
    Given Rest endpoint is up
    When Create new product
    Then Return status ok

  Scenario: Update product
    Given Rest endpoint is up
    When Update product by id 1
    Then Check product return

  Scenario: Delete product
    Given Rest endpoint is up
    When Delete product by id 3
    Then Returned product by id 3 is not found

