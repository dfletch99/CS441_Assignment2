# CS441_Assignment2 - Function grapher	
Using the GraphView widget, this app is able to plot any function of the formula y = ax^4 + bx^3 + dx^2 + fx + g where a,b,d,f,g are 
doubles.

The graph itself will plot multiple curves, with a "CLEAR" button below it to reset. The curves will rotate between six colors: black,
red, blue, purple, green, and yellow, to help distinguish between the different functions.

Tapping on the graph will create a temporary popup displaying the X and Y values. If there are multiple curves on the location tapped, the
popup will rotate through all the values.

To avoid having to type in multiple "0"s for functions with lower degree, the app also contains buttons that will change the highest 
possible degree that can be input: linear, quadratic, cubic, and quartic. For example, if the "linear" button is tapped, only the text
boxes for the values f and g are able to take input, and a,b and d are set to 0.

Under the graph viewport, there is another text box labeled "x = " and a button with text "JUMP TO COORDINATE". This feature works as one
would expect: the user inputs an x-coordinate, presses the button, and the graph jumps to a window where this x-coordinate is in the 
center, and the y-value range is appropriate based on the functions on the graph.
