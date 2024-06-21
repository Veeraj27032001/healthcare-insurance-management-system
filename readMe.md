<h1>  basic setup </h1>
1. load db dump file into Database

2. LOAD necessary data like country state city pin admin_features  patient_features site_details into databse ,other wise features will not be available 
(csv files are provided in data folder)

3. back end is spring boot and front end is React db is mysql

4. back end only accepts request from localhost:3000 .if your front end runing at different port replace accepted url from all 4 files of Controler

5. back end address is 8080 you can replaceit in env file if backend address is different

<h1>   patent features </h1>

6. patient features can be viewed in services or at bottom of home
7. patent has self registration , login , buy insurnace , my policy ,renew policy  (to acess fetures load  patient_features csv to table patient_features)



<h1>   admin features </h1>

8. add email and password into system with user data As "ADMIN" 
9. admin manages medical code ,insurnace provider and health care provider.

10. did not implement system management like country state so need to import country state city pin csv file data to their respective table

11. to acess admin features load admin_features csv data to table admin_features table

<h1>   insurnace  features </h1>

11. insurnace provider registered by admin he will generate login credential 
12. he can manage  insurnace policy .
13. need to load insuranceprovider_features csv file data to insuranceprovider_features to acess features
