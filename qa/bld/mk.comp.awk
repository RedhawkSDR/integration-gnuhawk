NR==1 {
    c="wc -l " FILENAME ;
    c | getline b ;
    split(b,nlines," ");
}
{   a=$1
    ##print nlines[1] NR $1
    if ( NR < nlines[1] ) 
	print  "./components/"$1"/cpp \\";
}
END { print "./components/"a"/cpp" } 
