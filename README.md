Am implementat swagger-ul, flyway-ul si am inceput migrarea bazelor de date.
API-ul trebuie regandit, nu cred ca merge conform cerintelor iar pe partea de securitate am avut probleme, am incercat sa implementez basic auth dar in swagger nu reuseam sa pun butonul de autorizare pentru API. In cele din urma, am scos partea de securitate, mentionez ca in Postman nu am avut probleme. Pe partea de FlyWay sper ca am implementat ok, am facut initializarea bazei de date. 

Cum functioneaza API-ul?

- Pe langa documentatia generata, API-ul selecteaza ID-urile date de la lista cu id-uri(introdusa manual in body) iar apoi preia cu ajutorul API-ului de la baza de date pentru filme, datele corespunzatoare ID-ului nostru, apoi insereaza campurile cerute in baza noastra de date sustinuta cu Postgre. 


Cum pornim aplicatia?
  - Deschidem codul sura intr-un editor de text
  - Dam Build apoi Run aplicatiei
  - Intram pe adresa localhost:8080 unde este hostata aplicatia noastra, cu portul respectiv
  - SWAGGER-UI: localhost:8080/swagger-ui.html
  
