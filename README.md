
📦 Warehouse Inventory Tracker (Event-Based)

🧠 Project Overview

The Warehouse Inventory Tracker is a Core Java–based event-driven system that helps a warehouse manage inventory in real-time.
It automatically updates stock levels when shipments arrive or orders are fulfilled and triggers restock alerts when quantities drop below the threshold.



⚙️ Features

Add new products dynamically

Receive shipments (increase stock)

Fulfill customer orders (decrease stock)

Automatic “Low Stock” alerts

Event-driven updates using the Observer Pattern

Error handling for invalid IDs and insufficient stock




🧩 Class Overview

Class	           Description

Product   	   Represents a product                   in the inventory

Warehouse 	    Manages inventory                          operations

StockObserver 	Interface for alert                        system 

AlertService	  Triggers alerts                     when stock is 

Main	        Demonstrates complete                     workflow




🚀 Example Workflow

1️⃣ Add product “Laptop” with reorder threshold of 5
2️⃣ Receive shipment of 10 units (total = 10)
3️⃣ Fulfill 6 orders (remaining = 4)
4️⃣ System triggers alert:

⚠️ Restock Alert: Low stock for Laptop – only 4 left!






🧠 Concepts Used

Object-Oriented Programming (Encapsulation, Abstraction)

Collections (HashMap)

Observer Design Pattern

Event-driven Architecture





🖥️ How to Run

1. Open the project in any IDE (VS Code / IntelliJ / Eclipse)


2. Compile and run Main.java


3. Observe real-time inventory updates and alerts in the console

