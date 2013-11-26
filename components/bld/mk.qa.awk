NR==1 {
    c="wc -l " FILENAME;
    c | getline b ;
    split(b,nlines," ");
}
{   a=$1
    ##print nlines[1] NR $1
    if ( NR < nlines[1] ) 
	print "../qa/components/"$1"/cpp \\";
}
END { 
    if ( NR > 0 )
	print "../qa/components/"a"/cpp ";
} 
