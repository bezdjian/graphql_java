# GraphQL with Java
 https://developer.okta.com/blog/2020/01/31/java-graphql

#### Run the app
`mvn spring-boot:run`

#### Browse 
http://localhost:8080/gui

# Query
##### Find 1 food
`{
   food(id: 1) {
     id
     name
   }
 }
`

##### Find all
`{
   foods {
     id
     name
   }
 }`

##### Save food
`mutation {
   saveFood(food:{name: "Pasta"}){
     id
     name
   }
 }`