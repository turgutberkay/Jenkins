@UserAcceptance
@ProductPage
Feature: ProductPage Feature

  Scenario: : Add to cart as login
    Given Go to "https://www.hepsiburada.com/"
    Given Login with "denemepoc123@gmail.com" email and "Denemepoc1" password
    When Check homepage
    When Clear basket for new scenarios
    When Search "kitap" product
    When Sort products by "En Çok Satanlar"
    When Click on any product and go to the product
    When 2 different sellers products are added to the cart
    Then Go to the cart and check the products

  Scenario: Add to cart without login
    Given Go to "https://www.hepsiburada.com/"
    When Search "kitap" product
    When Sort products by "En Çok Satanlar"
    When Click on any product and go to the product
    When 2 different sellers products are added to the cart
    Then Go to the cart and check the products







