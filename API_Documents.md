# Common Parts

> For three types of entity, todo, group and expense

## Http Method and URL

1. Create: Post
2. Update: Put /{id}
3. List: Get 
4. Get: Get /{id}
5. Delete: Delete /{id}

## Exceptions
1. Create
   1. Format exception
   2. Inactive Account 

2. Update
   1. Entity does not exist
   2. Format exception
   3. No permission
   4. Inactive Account

3. List
   1. Inactive Account 

4. Get
   1. Entity does not exist
   2. No permission
   3. Inactive Account 

5. Delete
   1. Entity does not exist
   2. No permission
   3. Inactive Account 


## Exception Http Status Code

1. Bad Request 400
2. Forbidden 403


# Business Exception(Bad Request)
## Sign Up

1. User exists
2. Format exception
   1. Password is not as the same as re password
3. Email service error (Internal Server error)

## Verify

1. Expired Link
2. Invliad Link
3. User does not exist
4. Already verified

## Get Invite link

1. Cannot invite yourself
2. Inactive account [who is invited]
3. Inactive account [who is going to invite]
4. Already in your group
5. Email service error (Internal Server error)

## Join group

1. Expired Link
2. Invalid Link
3. Already join this group
4. Belong to other group
5. Inactive account





