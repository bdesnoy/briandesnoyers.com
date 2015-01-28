BAKERY MANAGER- OBJECT ORIENTED DESIGN ASSIGNMENT 11
GROUP 10: BRIAN DESNOYERS AND LUCAS MAGEE
README FILE

NOTE: BlackBox testing results are located in 
	BlackBoxTesting.pdf.
====================================================

01	Starting the bakery manager program

		The user interface can be run via
		the main method in the BakeryManager
		class within the bakery package.

02	Constructing an empty bakery
	
		By launching the bakery manager
		program as described in section 1,
		a new bakery state will be
		constructed automatically.

03	Initialize the bakery from two files
		Be sure that the file containing the
		list of available bakery items is
		stored within the application
		directory and is named bakeryItems.txt
		and that the file containing the list
		of all orders is stored within the
		application directory is saved as
		orders.txt.

		Select “File, Import Bakery Records”.
		The system will ask you to confirm
		that the files are named correctly
		and placed in the application directory.
		If the conditions are met, click “OK”.
		The system will begin to input the files.

		If the files are corrupted or unable to
		be read, the system will produce a
		warning.
	
		Since customers are imported based on ids
		and not names (as multiple customers can have
		the same name in this system), always
		be sure that there are no conflicting ids
		in the file with those already in the
		database. The best way to do this is to 
		always initialize an empty bakery before
		making any modifications to it.

04	Add new customers to the bakery through the 
	user interface
		
		To add a customer, select “Add Customer”,
		which is listed under “Customer Management”.
		The system will prompt you to enter the
		customer’s last name, enter the customer’s
		street address, enter the customer’s city,
		select the customer’s state from a list, 
		and enter the customer’s zip code. Select 
		“OK” and the customer will be added.

		New customers can also be added at any time
		a customer must be selected. For example,
		when adding a new order, a customer can be
		added by selecting “Add a new customer” when
		prompted to select the customer.

05	Update customer information through the user
	interface
		
		To update customer information, select 
		“Edit Customer”, which is listed under
		“Customer Management”. When prompted to
		select a customer, select the customer 
		that you would like to edit and press “OK”.
		The system will then prompt for how the
		customer should be edited. The user can 
		then select either “Edit customer name” or
		“Edit customer residence”.

		If “Edit customer name” is selected, the
		system will prompt the user to enter the
		new customer name. When the user selects
		“OK” the system will update the client’s
		name (the old name will be the default
		input).

		If “Edit customer residence” is selected,
		the system will prompt the user to enter 
		the updated street address (with the old
		street address being the default input),
		the updated city (with the old city being
		the default input), the updated state
		(with the old state being the default
		selection), and the zip code (with the old
		zip code being the default input).When the 
		user selects “OK”, the system will update 
		the client’s residence information.


06	Update orders

		To update an order, select “Edit Order”, 
		which is listed under “Bakery Management”.
		Select the customer that whose order needs
		to be edited and click “OK”. Select the 
		order that needs to be edited and click
		“OK”. Select either “Edit payment status”,
		“Edit item quantity”, or “Add item” and
		and follow the prompts to make the order
		updates.

07	Add new bakery items to the system

		To add a new bakery item, select “Add
		Item” which is listed under “Item 
		Management”. Enter the name of the bakery
		item to add and select “OK”. Select the 
		item category or select “Add a new category”
		and follow the prompts to add a new category.
		Enter the item price and select “OK”.
		The item will be added.

08	Update bakery items

		To update a bakery item, select “Edit
		Item”, which is listed under “Item Management”.
		Select the item to edit and click “OK”.
		Select “Edit item name”, “Edit item category”,
		or “Edit item price”, and select “OK”. Follow
		the prompts to edit the item.

09	View loyalty card information for a specific customer

		To view loyalty card information for a 
		specific customer, select “Show Customer
		Information”, which is listed under
		“Customer Management”. Select the customer
		and select “OK”. The customer’s name, residence,
		and loyalty card information will be shown.

	View contact information for a specific customer
	
		To view contact information for a 
		specific customer, select “Show Customer
		Information”, which is listed under
		“Customer Management”. Select the customer
		and select “OK”. The customer’s name, residence,
		and loyalty card information will be shown.

	View all Orders by a Specific Customer

		Select “Printed Reports” which is listed under
		“Bakery Management”. Select “Orders By Customer”.
		Select the customer. The receipts from the orders
		by that customer will be printed to the command
		line.

	View all Orders Placed on a Specific Date

		Select “Printed Reports” which is listed under
		“Bakery Management”. Select “Orders By Order Date”.
		Select the order date. The receipts from the orders
		by that order date will be printed to the command
		line.

	View all Orders with a Specific Pick-up Date

		Select “Printed Reports” which is listed under
		“Bakery Management”. Select “Orders By Pickup Date”.
		Select the pickup date. The receipts from the orders
		by that pickup date will be printed to the command
		line.

	View all Orders for a Specific Product

		Select “Printed Reports” which is listed under
		“Bakery Management”. Select “Orders By Item”.
		Select the item. The receipts from the orders
		that include that item will be printed to the 
		command line.

	View all Unpaid Orders

		Select “Printed Reports” which is listed under
		“Bakery Management”. Select “Unpaid Orders”.
		The receipts from the orders that are unpaid
		will be printed to the command line.

	View Menu /list of all Bakery Items

		Select “Printed Reports” which is listed under
		“Bakery Management”. Select “Items for Sale”.
		A list of all items for sale will be printed to
		the command line.

10	Output the current state of the system into two files
	
		The output will write to bakeryItems.txt and
		orders.txt in the application directory. Any
		previous files of those names will be overwritten.

		Select “File, Export to Bakery Records”.

		Select “OK” to the overwrite warning. 

		The system will show an alert if it was unable
		to write to the files.

