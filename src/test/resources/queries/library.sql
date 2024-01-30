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



-- .....