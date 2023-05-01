class Link
{
    public int iData;
    public double dData;
    public Link next;

    public Link(int id, double dd)
    {
        iData = id;
        dData = dd;
    }
    public void displayLink()
    {
        System.out.print("{" + iData + ", " + dData + "} ");
    }

}

class aLink
{
    public long dData;                 // data item
    public aLink next;                  // next link in list
    public aLink(long d)                // constructor
    { dData = d; }
    public void displayaLink()          // display this link
    { System.out.print(dData + " "); }

}

class bLink
{
    public long dData;                 // data item
    public bLink next;                  // next link in list
    public bLink previous;              // previous link in list
    public bLink(long d)                // constructor
    { dData = d; }

    public void displaybLink()          // display this link
    { System.out.print(dData + " "); }

}

class LinkList
{
    private Link first;
    public LinkList()
    {
        first = null;
    }

    public void insertFirst(int id, double dd)
    {
        Link newLink = new Link(id, dd);
        newLink.next = first;
        first = newLink;
    }

    public Link find(int key)
    {
        Link current = first;
        while(current.iData != key)
        {
            if(current.next == null)
                return null;
            else
                current = current.next;
        }
        return current;
    }

    public Link delete(int key) {

        Link current = first;
        Link previous = null;
        while (current != null && current.iData != key) {
            previous = current;
            current = current.next;
        }
        if (current == null) {
            return null; // didn't find it
        }
        if (previous == null) { // deleting first link
            first = current.next;
        } else {
            previous.next = current.next;
        }
        return current;
    }

    public void displayList() // display the list
    {
        System.out.print("List (first-->last): ");
        Link current = first; // start at beginning of list
        while(current != null) // until end of list,
        {
            current.displayLink(); // print data
            current = current.next; // move to next link
        }
        System.out.println("");
    }
}

class FirstLastList {
    private aLink first; // ref to first link
    private aLink last; // ref to last link

    public FirstLastList() {
        first = null; // no links on list yet
        last = null;
    }

    public boolean isEmpty() {
        return first == null;
    }

    public void insertFirst(long dd) {

        aLink newLink = new aLink(dd); // make new link

        if (isEmpty()) // if empty list,
            last = newLink; // newLink <-- last

        newLink.next = first; // newLink --> old first
        first = newLink; // first --> newLink
    }


    public void insertLast(long dd) {
        aLink newLink = new aLink(dd); // make new link

        if (isEmpty()) // if empty list,
            first = newLink; // first --> newLink
        else
            last.next = newLink; // old last --> newLink

        last = newLink; // newLink <-- last
    }

    // delete first link (assumes non-empty list)
    public long deleteFirst() {
        long temp = (long) first.dData;

        if (first.next == null) // if only one item
            last = null; // null <-- last

        first = first.next; // first --> old next
        return temp;
    }

    public long deleteLast() {
        long temp = (long) last.dData;

        if (first.next == null) { // if only one item
            first = null;
            last = null;
        } else {
            aLink current = first;
            while (current.next != last)
                current = current.next;

            last = current;
            last.next = null;
        }

        return temp;
    }

    public void displayList() {
        System.out.print("List (first-->last): ");

        aLink current = first;
        while (current != null) {
            current.displayaLink();
            current = current.next;
        }

        System.out.println("");
    }

}

class DoublyLinkedList {
    public bLink first;               // ref to first item
    public bLink last;                // ref to last item

    public DoublyLinkedList()         // constructor
    {
        first = null;                  // no items on list yet
        last = null;
    }

    public boolean isEmpty()
    {
        return first == null;
    }

    public void insertFirst(long dd)
    {
        bLink newLink = new bLink(dd);

        if (isEmpty())
            last = newLink;
        else
            first.previous = newLink;   // newLink <-- old first
        newLink.next = first;          // newLink --> old first
        first = newLink;               // first --> newLink

    }

    public bLink deleteKey(long key)   // delete item w/ given key
    {                              // (assumes non-empty list)
        bLink current = first;          // start at beginning
        while(current.dData != key)    // until match is found,
        {
            current = current.next;     // move to next link
            if(current == null)
                return null;             // didn't find it
        }
        if(current==first)             // found it; first item?
            first = current.next;       // first --> old next
        else                           // not first
            // old previous --> old next
            current.previous.next = current.next;

        if(current==last)              // last item?
            last = current.previous;    // old previous <-- last
        else                           // not last
            // old previous <-- old next
            current.next.previous = current.previous;
        return current;                // return value
    }
}

class Main
{
    public static void main(String[] args)
    {
        LinkList theList = new LinkList();

        theList.insertFirst(22, 2.99);
        theList.insertFirst(44, 4.99);
        theList.insertFirst(66, 6.99);
        theList.insertFirst(88, 8.99);
        theList.displayList();

        Link f = theList.find(44);

        if( f != null)
            System.out.println("Found link with key " + f.iData);
        else
            System.out.println("Can’t find link");

        Link d = theList.delete(66);

        if( d != null )
            System.out.println("Deleted link with key " + d.iData);
        else
            System.out.println("Can’t delete link");

        theList.displayList();

        FirstLastList theList2 = new FirstLastList();

        theList2.insertFirst(22);
        theList2.insertFirst(44);
        theList2.insertFirst(66);

        theList2.insertLast(11);
        theList2.insertLast(33);
        theList2.insertLast(55);
        System.out.println("");

        theList2.displayList();

        theList2.deleteFirst();
        theList2.deleteFirst();

        theList2.displayList();
        theList2.deleteLast();
        theList2.displayList();

        Main doubleList = new Main();

        System.out.println(doubleList.lastInteger(1000,600));


    }

    public long lastInteger(long n,long k) {

        DoublyLinkedList theList = new DoublyLinkedList();
        long i,t = 1, j = 0;

        for (i = n; i > 0; i--)
            theList.insertFirst(i);

        bLink current = theList.first;

        while (current.next != null || current.previous != null) {

            //Right

            for (i = j; j < k; j++) {

                if (current.next != null) {
                    current = current.next;
                    t++;

                    if (t == k) {
                        if (current.next != null) {
                            current = current.next;
                            theList.deleteKey(current.previous.dData);
                            t=1;
                            j=0;

                        } else {
                            current = current.previous;
                            theList.deleteKey(current.next.dData);
                            t=1;
                            j=0;
                            break;
                        }

                    }

                }
                else
                    break;
            }

            // Left

            for (i = j; j < k; j++) {
                if (current.previous != null) {
                    current = current.previous;
                    t++;

                    if (t == k) {
                        if (current.previous != null) {
                            current = current.previous;
                            theList.deleteKey(current.next.dData);
                            t=1;
                            j=0;

                        } else {
                            current = current.next;
                            theList.deleteKey(current.previous.dData);
                            t=1;
                            j=0;
                            break;
                        }

                    }

                }
                else
                    break;
            }
        }
        return current.dData;
    }
}