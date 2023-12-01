# Request Mapping Engine

## Overview
The Request Mapping Engine is a custom solution, designed to function within a load balancing environment, to optimize the distribution of web application traffic using a consistent hashing algorithm. The core of the engine uses SHA-256 for hashing and a TreeMap for maintaining a consistent hash ring, ensuring efficient and evenly distributed traffic management. 

![Working figure](https://github.com/sinhatanmay18/request-mapping-engine/assets/76418883/7c1bf3d7-e500-4b09-8097-f70ea8a27655)
                                              

## Hash Ring
A hash ring is a data structure used in consistent hashing, an algorithm designed to distribute load (like network traffic, data storage) evenly across a set of servers or nodes. The hash ring represents a circular space where each point on the ring corresponds to a hash value. 

![Concept of Hash Ring](https://github.com/sinhatanmay18/request-mapping-engine/assets/76418883/cd85729e-6812-464e-a645-02865d0a893b)



## Key Characteristics of a Hash Ring:
    1. Circular Nature: The ring is a continuous loop. This means that after the highest hash value, it wraps back around to the smallest hash value.
    2. Fixed Range: The ring covers a fixed range of hash values, determined by the hash function, in our case, from 0 to 2^64 – 1.
       
## Placing Servers on the Hash Ring

Each server in our system is assigned a position on the hash ring. This position is determined by hashing server-specific data using SHA-256. In my implementation, I have used IP Address and Port number because they are unique to every server thereby reducing risk of collision and each server gets a fairly distributed share of user requests. 

Here’s how we use IP and Port number to plot the server on the hash ring:

![Server Hashing](https://github.com/sinhatanmay18/request-mapping-engine/assets/76418883/f4a64c3a-16a2-4592-b34e-c645815ad9d8)
                                                      

The hash space which is in the form of sorted map offers a natural ordering of its keys, ensuring that when I receive a hashed value from user details or server information, I can efficiently find its position or the closest key in the map. This allows for quick server lookups and ensures that requests are directed to the correct server.


## Hashing User Requests
User requests are mapped to servers based on hashed user identifiers, such as email and username. The hashing process identifies the appropriate server on the hash ring to handle each request. Here’s how user requests are hashed and servers are allotted to incoming requests:

![User Request hashing](https://github.com/sinhatanmay18/request-mapping-engine/assets/76418883/f8845da7-7b45-45f4-8cb8-5b022e3ebc06)
                                                    
![Finding Nearest server](https://github.com/sinhatanmay18/request-mapping-engine/assets/76418883/18cd3e75-d743-4494-b5b0-301e72d0964d)
                                                     
