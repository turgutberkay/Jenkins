@UserAcceptance
@LoginPage
Feature: LoginPage Feature

  Scenario Outline: : Login with false email
    Given Go to "https://www.hepsiburada.com/"
    When Login with <email> email and enter
    Then Should see false email message <falseMailMessage>
    Examples:
      | email                    | falseMailMessage                         |
      | berkay@@gmail.com        | Geçerli bir e-posta adresi girmelisiniz. |
      | berkay@gmail..com        | Geçerli bir e-posta adresi girmelisiniz. |

  Scenario Outline: Login with empty email
    Given Go to "https://www.hepsiburada.com/"
    When Login with <email> email and enter
    Then Should see empty email message <emptyMailMessage>
    Examples:
      | email | emptyMailMessage                                         |
      |       | E-posta adresinizi veya telefon numaranızı girmelisiniz. |

  Scenario Outline: Login with empty password
    Given Go to "https://www.hepsiburada.com/"
    When Login with "denemepoc123@gmail.com" email and enter
    When Login with <password> password and enter
    Then Should see empty password message <emptyPasswordMessage>
    Examples:
      | password | emptyPasswordMessage                                         |
      |          | Şifrenizi girmelisiniz.                                      |



