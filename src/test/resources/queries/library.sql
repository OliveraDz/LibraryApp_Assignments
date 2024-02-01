SELECT * FROM books;

-- US 01 -1
  -- OPT 1
      select id from users;
      -- getColumnDataAsList --> List --> size --> 10
      -- getColumnDataAsList --> Set  --> size --> 10

  -- OPT 2
    select count(id) from users; -- 5277          --> ACTUAL
    select count(distinct id) from users; -- 5277 --> EXPECTED

-- US 01 -2
    select * from users;

    -- OPT 2
    select COLUMN_NAME
    from information_schema.columns
    where TABLE_NAME='users';

-- US 02
    select count(*) from book_borrow
    where is_returned=0;
    -- 0 -> Book in user
    -- 1 -> Book in library

-- US 03
    select name from book_categories;

    -- Join 4 table to see same data from UI
        select isbn,b.name,author,bc.name,year,full_name
        from users u
            inner join book_borrow bb on u.id=bb.user_id
            inner join books b on bb.book_id = b.id
            inner join book_categories bc on b.book_category_id = bc.id;
-- .....

-- US 04
    select b.name as bookName,isbn,author,year,b.description,bc.name as bookCategory
    from books b
            inner join book_categories bc on b.book_category_id=bc.id
    where b.name='Clean Code';

-- US 05
    select bc.name,count(*)
    from book_borrow bb
        inner join books b on bb.book_id = b.id
        inner join book_categories bc on b.book_category_id = bc.id
    group by bc.name
    order by count(*) desc
    limit 1;

