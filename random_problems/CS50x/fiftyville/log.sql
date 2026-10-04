-- Keep a log of any SQL queries you execute as you solve the mystery.

-- left bakery between 10:15 to 10:25
SELECT people.name,
bakery_security_logs.month as bslmonth,
bakery_security_logs.day as bslday,
bakery_security_logs.hour as bslhour,
bakery_security_logs.minute as bslminute
FROM bakery_security_logs
JOIN people ON bakery_security_logs.license_plate = people.license_plate
WHERE bakery_security_logs.year = 2024
AND bakery_security_logs.month = 7
AND bakery_security_logs.day = 28
AND bakery_security_logs.hour >= 10
AND bakery_security_logs.minute >= 15
AND bakery_security_logs.minute <= 25
AND activity = 'exit';

-- withdraw money at Leggett Street
SELECT people.name, atm_transactions.amount as amount, atm_transactions.transaction_type, atm_transactions.atm_location as atm_location,
atm_transactions.month, atm_transactions.day
FROM people
JOIN passengers ON passengers.passport_number = people.passport_number
JOIN bank_accounts ON bank_accounts.person_id = people.id
JOIN atm_transactions ON atm_transactions.account_number = bank_accounts.account_number
WHERE atm_transactions.year = 2024
AND atm_transactions.month = 7
AND atm_transactions.day = 28
AND atm_location = 'Leggett Street'
AND transaction_type = "withdraw";

-- called each  other for less than a minute
SELECT people.name as caller_name, people.phone_number as caller_number, phone_call.duration, phone_call.receiver_name
FROM people,
    (
    SELECT p1.name AS caller_name,
    p1.phone_number AS caller_number,
    p2.name AS receiver_name,
    p2.phone_number AS receiver_number,
    pc.duration,
    pc.caller as pcaller,
    pc.receiver as preceiver
    FROM phone_calls pc
    JOIN people p1 ON pc.caller = p1.phone_number
    JOIN people p2 ON pc.receiver = p2.phone_number
    WHERE pc.year = 2024
    AND pc.month = 7
    AND pc.day = 28
    AND pc.duration < 60) as phone_call
WHERE (phone_number = phone_call.pcaller OR phone_number = phone_call.preceiver) AND phone_call.caller_name != phone_call.receiver_name

-- flight out of fiftyville
SELECT people.name, people.passport_number,
origin.full_name as origin,
destination.full_name as destination,
flights.month as flight_month,
flights.day as flight_day
FROM people
JOIN passengers ON passengers.passport_number = people.passport_number
JOIN flights ON flights.id = passengers.flight_id
JOIN airports as origin ON origin.id = flights.origin_airport_id
JOIN airports as destination ON destination.id = flights.destination_airport_id
WHERE flights.year = 2024
AND flights.month = 7
AND flights.day >= 28
AND origin.full_name = 'Fiftyville Regional Airport') as flew_out_fiftyville ON flew_out_fiftyville.name = left_bakery_time_range.name;

-- final
SELECT
    left_bakery_time_range.name,
    bslmonth, bslday, bslhour, bslminute,
    leggett_street_withdrawers.atm_location,
    phone_call.caller_number,
    phone_call.duration
FROM (-- left bakery between 10:15 to 10:25
    SELECT people.name,
    bakery_security_logs.month as bslmonth,
    bakery_security_logs.day as bslday,
    bakery_security_logs.hour as bslhour,
    bakery_security_logs.minute as bslminute
    FROM bakery_security_logs
    JOIN people ON bakery_security_logs.license_plate = people.license_plate
    WHERE bakery_security_logs.year = 2024
    AND bakery_security_logs.month = 7
    AND bakery_security_logs.day = 28
    AND bakery_security_logs.hour >= 10
    AND bakery_security_logs.minute >= 15
    AND bakery_security_logs.minute <= 25) as left_bakery_time_range
JOIN (-- withdraw money at Leggett Street
    SELECT people.name, atm_transactions.amount as amount, atm_transactions.transaction_type, atm_transactions.atm_location as atm_location,
    atm_transactions.month, atm_transactions.day
    FROM people
    JOIN passengers ON passengers.passport_number = people.passport_number
    JOIN bank_accounts ON bank_accounts.person_id = people.id
    JOIN atm_transactions ON atm_transactions.account_number = bank_accounts.account_number
    WHERE atm_transactions.year = 2024
    AND atm_transactions.month = 7
    AND atm_transactions.day = 28
    AND atm_location = 'Leggett Street'
    ) as leggett_street_withdrawers ON leggett_street_withdrawers.name = left_bakery_time_range.name
JOIN (-- called each  other for less than a minute
    SELECT people.name as caller_name, people.phone_number as caller_number, phone_call.duration, phone_call.receiver_name
    FROM people,
        (
        SELECT p1.name AS caller_name,
        p1.phone_number AS caller_number,
        p2.name AS receiver_name,
        p2.phone_number AS receiver_number,
        pc.duration,
        pc.caller as pcaller,
        pc.receiver as preceiver
        FROM phone_calls pc
        JOIN people p1 ON pc.caller = p1.phone_number
        JOIN people p2 ON pc.receiver = p2.phone_number
        WHERE pc.year = 2024
        AND pc.month = 7
        AND pc.day = 28
        AND pc.duration < 60) as phone_call
    WHERE (phone_number = phone_call.pcaller OR phone_number = phone_call.preceiver) AND phone_call.caller_name != phone_call.receiver_name) as phone_call ON phone_call.caller_name = left_bakery_time_range.name OR phone_call.receiver_name = left_bakery_time_range.name;

SELECT
    people.name, people.passport_number,
    origin.full_name as origin,
    destination.full_name as destination,
    flights.month as flight_month,
    flights.day as flight_day,
    destination.city
FROM people
JOIN passengers ON passengers.passport_number = people.passport_number
JOIN flights ON flights.id = passengers.flight_id
JOIN airports as origin ON origin.id = flights.origin_airport_id
JOIN airports as destination ON destination.id = flights.destination_airport_id
WHERE people.name = "Bruce"
    AND flights.year = 2024
    AND flights.month = 7
    AND flights.day >= 28
    AND origin.full_name = 'Fiftyville Regional Airport';

-- The Accomplace:
SELECT people.name
FROM people
WHERE people.phone_number IN (
	SELECT receiver
	FROM phone_calls
	WHERE year = 2024
    AND month = 7
    AND day = 28
	AND duration < 60
	AND caller = (
	SELECT people.phone_number
        FROM people
        WHERE people.name = "Bruce"
    )
);













SELECT
    left_bakery_time_range.name,
    leggett_street_withdrawers.atm_location,
    phone_call.caller_number,
    phone_call.duration,
    flew_out_fiftyville.origin,
    flew_out_fiftyville.destination,
    flew_out_fiftyville.flight_month,
    flew_out_fiftyville.flight_day
FROM (-- left bakery between 10:15 to 10:25
    SELECT people.name
    FROM bakery_security_logs
    JOIN people ON bakery_security_logs.license_plate = people.license_plate
    WHERE bakery_security_logs.year = 2024
    AND bakery_security_logs.month = 7
    AND bakery_security_logs.day = 28
    AND bakery_security_logs.hour >= 10
    AND bakery_security_logs.minute >= 15
    AND bakery_security_logs.minute <= 25) as left_bakery_time_range
JOIN (-- withdraw money at Leggett Street
    SELECT people.name, atm_transactions.amount as amount, atm_transactions.transaction_type, atm_transactions.atm_location as atm_location,
    atm_transactions.month, atm_transactions.day
    FROM people
    JOIN passengers ON passengers.passport_number = people.passport_number
    JOIN bank_accounts ON bank_accounts.person_id = people.id
    JOIN atm_transactions ON atm_transactions.account_number = bank_accounts.account_number
    WHERE atm_transactions.year = 2024
    AND atm_transactions.month = 7
    AND atm_transactions.day = 28
    AND atm_location = 'Leggett Street'
    ) as leggett_street_withdrawers ON leggett_street_withdrawers.name = left_bakery_time_range.name
JOIN (-- called each  other for less than a minute
    SELECT people.name as caller_name, people.phone_number as caller_number, phone_call.duration, phone_call.receiver_name
    FROM people,
        (
        SELECT p1.name AS caller_name,
        p1.phone_number AS caller_number,
        p2.name AS receiver_name,
        p2.phone_number AS receiver_number,
        pc.duration,
        pc.caller as pcaller,
        pc.receiver as preceiver
        FROM phone_calls pc
        JOIN people p1 ON pc.caller = p1.phone_number
        JOIN people p2 ON pc.receiver = p2.phone_number
        WHERE pc.year = 2024
        AND pc.month = 7
        AND pc.day = 28
        AND pc.duration < 60) as phone_call
    WHERE phone_number = phone_call.pcaller OR phone_number = phone_call.preceiver) as phone_call ON phone_call.caller_name = left_bakery_time_range.name OR phone_call.receiver_name = left_bakery_time_range.name
JOIN (-- flight out of fiftyville
    SELECT people.name, people.passport_number,
    origin.full_name as origin,
    destination.full_name as destination,
    flights.month as flight_month,
    flights.day as flight_day
    FROM people
    JOIN passengers ON passengers.passport_number = people.passport_number
    JOIN flights ON flights.id = passengers.flight_id
    JOIN airports as origin ON origin.id = flights.origin_airport_id
    JOIN airports as destination ON destination.id = flights.destination_airport_id
    WHERE flights.year = 2024
    AND flights.month = 7
    AND flights.day >= 28
    AND origin.full_name = 'Fiftyville Regional Airport') as flew_out_fiftyville ON flew_out_fiftyville.name = left_bakery_time_range.name;


-- final
SELECT
    left_bakery_time_range.name,
    bslmonth, bslday, bslhour, bslminute,
    leggett_street_withdrawers.atm_location,
    phone_call.caller_number,
    phone_call.duration,
    flew_out_fiftyville.origin,
    flew_out_fiftyville.destination,
    flew_out_fiftyville.flight_month,
    flew_out_fiftyville.flight_day
FROM (-- left bakery between 10:15 to 10:25
    SELECT people.name,
    bakery_security_logs.month as bslmonth,
    bakery_security_logs.day as bslday,
    bakery_security_logs.hour as bslhour,
    bakery_security_logs.minute as bslminute
    FROM bakery_security_logs
    JOIN people ON bakery_security_logs.license_plate = people.license_plate
    WHERE bakery_security_logs.year = 2024
    AND bakery_security_logs.month = 7
    AND bakery_security_logs.day = 28
    AND bakery_security_logs.hour >= 10
    AND bakery_security_logs.minute >= 15
    AND bakery_security_logs.minute <= 25) as left_bakery_time_range
JOIN (-- withdraw money at Leggett Street
    SELECT people.name, atm_transactions.amount as amount, atm_transactions.transaction_type, atm_transactions.atm_location as atm_location,
    atm_transactions.month, atm_transactions.day
    FROM people
    JOIN passengers ON passengers.passport_number = people.passport_number
    JOIN bank_accounts ON bank_accounts.person_id = people.id
    JOIN atm_transactions ON atm_transactions.account_number = bank_accounts.account_number
    WHERE atm_transactions.year = 2024
    AND atm_transactions.month = 7
    AND atm_transactions.day = 28
    AND atm_location = 'Leggett Street'
    ) as leggett_street_withdrawers ON leggett_street_withdrawers.name = left_bakery_time_range.name
JOIN (-- called each  other for less than a minute
    SELECT people.name as caller_name, people.phone_number as caller_number, phone_call.duration, phone_call.receiver_name
    FROM people,
        (
        SELECT p1.name AS caller_name,
        p1.phone_number AS caller_number,
        p2.name AS receiver_name,
        p2.phone_number AS receiver_number,
        pc.duration,
        pc.caller as pcaller,
        pc.receiver as preceiver
        FROM phone_calls pc
        JOIN people p1 ON pc.caller = p1.phone_number
        JOIN people p2 ON pc.receiver = p2.phone_number
        WHERE pc.year = 2024
        AND pc.month = 7
        AND pc.day = 28
        AND pc.duration < 60) as phone_call
    WHERE (phone_number = phone_call.pcaller OR phone_number = phone_call.preceiver) AND phone_call.caller_name != phone_call.receiver_name) as phone_call ON phone_call.caller_name = left_bakery_time_range.name OR phone_call.receiver_name = left_bakery_time_range.name
JOIN (-- flight out of fiftyville
    SELECT people.name, people.passport_number,
    origin.full_name as origin,
    destination.full_name as destination,
    flights.month as flight_month,
    flights.day as flight_day
    FROM people
    JOIN passengers ON passengers.passport_number = people.passport_number
    JOIN flights ON flights.id = passengers.flight_id
    JOIN airports as origin ON origin.id = flights.origin_airport_id
    JOIN airports as destination ON destination.id = flights.destination_airport_id
    WHERE flights.year = 2024
    AND flights.month = 7
    AND flights.day >= 28
    AND origin.full_name = 'Fiftyville Regional Airport') as flew_out_fiftyville ON flew_out_fiftyville.name = left_bakery_time_range.name;



-- 1
SELECT DISTINCT
    left_bakery_time_range.name,
    leggett_street_withdrawers.atm_location,
    phone_call.caller_number,
    phone_call.duration
FROM (-- left bakery between 10:15 to 10:25
    SELECT people.name
    FROM bakery_security_logs
    JOIN people ON bakery_security_logs.license_plate = people.license_plate
    WHERE bakery_security_logs.year = 2024
    AND bakery_security_logs.month = 7
    AND bakery_security_logs.day = 28
    AND bakery_security_logs.hour >= 10
    AND bakery_security_logs.minute >= 15
    AND bakery_security_logs.minute <= 25) as left_bakery_time_range
JOIN (-- withdraw money at Leggett Street
    SELECT people.name, atm_transactions.amount as amount, atm_transactions.transaction_type, atm_transactions.atm_location as atm_location,
    atm_transactions.month, atm_transactions.day
    FROM people
    JOIN passengers ON passengers.passport_number = people.passport_number
    JOIN bank_accounts ON bank_accounts.person_id = people.id
    JOIN atm_transactions ON atm_transactions.account_number = bank_accounts.account_number
    WHERE atm_transactions.year = 2024
    AND atm_transactions.month = 7
    AND atm_transactions.day = 28
    AND atm_location = 'Leggett Street'
    ) as leggett_street_withdrawers ON leggett_street_withdrawers.name = left_bakery_time_range.name
JOIN (-- called each  other for less than a minute
    SELECT people.name as caller_name, people.phone_number as caller_number, phone_call.duration, phone_call.receiver_name
    FROM people,
        (
        SELECT p1.name AS caller_name,
        p1.phone_number AS caller_number,
        p2.name AS receiver_name,
        p2.phone_number AS receiver_number,
        pc.duration,
        pc.caller as pcaller,
        pc.receiver as preceiver
        FROM phone_calls pc
        JOIN people p1 ON pc.caller = p1.phone_number
        JOIN people p2 ON pc.receiver = p2.phone_number
        WHERE pc.year = 2024
        AND pc.month = 7
        AND pc.day = 28
        AND pc.duration < 60) as phone_call
    WHERE phone_number = phone_call.pcaller OR phone_number = phone_call.preceiver) as phone_call ON phone_call.caller_name = left_bakery_time_range.name OR phone_call.receiver_name = left_bakery_time_range.name;


SELECT * FROM crime_scene_reports
WHERE year = 2024 AND month = 7 AND day = 28 AND street LIKE '%Humphrey%';

SELECT * FROM bakery_security_logs
WHERE year = 2024 AND month = 7 AND day = 28;

-- Only select before 10:15am, since theft happened at 10:15am.
SELECT id, hour, minute, activity, license_plate FROM bakery_security_logs
WHERE year = 2024
AND month = 7
AND day = 28
AND hour <= 10
AND minute <= 15;

-- Only select before 10:15am, since theft happened at 10:15am.
-- with the license_plate owner name
SELECT bakery_security_logs.hour, bakery_security_logs.minute, bakery_security_logs.activity, bakery_security_logs.license_plate,
people.name, people.passport_number
FROM bakery_security_logs
JOIN people ON bakery_security_logs.license_plate = people.license_plate
WHERE bakery_security_logs.year = 2024
AND bakery_security_logs.month = 7
AND bakery_security_logs.day = 28
AND bakery_security_logs.hour <= 10
AND bakery_security_logs.minute <= 15;

-- Only select before 10:15am, since theft happened at 10:15am.
-- with the license_plate owner name
-- with passport_number referencing flights
SELECT bakery_security_logs.hour, bakery_security_logs.minute, bakery_security_logs.activity, bakery_security_logs.license_plate,
people.name, people.passport_number
FROM bakery_security_logs
JOIN people ON bakery_security_logs.license_plate = people.license_plate
JOIN passengers ON passengers.passport_number = people.passport_number
JOIN flights ON flights.id = passengers.flight_id
WHERE bakery_security_logs.year = 2024
AND bakery_security_logs.month = 7
AND bakery_security_logs.day = 28
AND bakery_security_logs.hour <= 10
AND bakery_security_logs.minute <= 15;

-- Only select before 10:15am, since theft happened at 10:15am.
-- with the license_plate owner name
-- with passport_number referencing flights
SELECT bakery_security_logs.hour, bakery_security_logs.minute, bakery_security_logs.activity, bakery_security_logs.license_plate,
people.name, people.passport_number,
airports.full_name,
flights.month, flights.day,
atm_transactions.amount, atm_transactions.transaction_type, atm_transactions.atm_location
FROM bakery_security_logs
JOIN people ON bakery_security_logs.license_plate = people.license_plate
JOIN passengers ON passengers.passport_number = people.passport_number
JOIN flights ON flights.id = passengers.flight_id
JOIN airports ON airports.id = flights.origin_airport_id
JOIN bank_accounts ON bank_accounts.person_id = people.id
JOIN atm_transactions ON atm_transactions.account_number = bank_accounts.account_number
WHERE bakery_security_logs.year = 2024
AND bakery_security_logs.month = 7
AND bakery_security_logs.day = 28
AND bakery_security_logs.hour >= 10
AND bakery_security_logs.minute >= 15
AND flights.year = 2024
AND flights.month = 7
AND flights.day >= 28
AND flights.hour >= 10
AND flights.minute >= 15
AND airports.full_name = 'Fiftyville Regional Airport'
AND atm_transactions.year = 2024
AND atm_transactions.month = 7
AND atm_transactions.day = 28
AND atm_location = 'Leggett Street'
ORDER BY bakery_security_logs.hour, bakery_security_logs.minute;


-- called each  other for less than a minute
SELECT p1.name AS caller_name,
p1.phone_number AS caller_number,
p2.name AS receiver_name,
p2.phone_number AS receiver_number,
pc.duration
FROM phone_calls pc
JOIN people p1 ON pc.caller = p1.phone_number
JOIN people p2 ON pc.receiver = p2.phone_number
WHERE pc.year = 2024
AND pc.month = 7
AND pc.day = 28
AND pc.duration < 60;

-- called each  other for less than a minute
SELECT people.name, people.phone_number, phone_call.duration
FROM people,
    (SELECT people.name,
    phone_calls.caller as pcaller,
    phone_calls.receiver as preceiver,
    phone_calls.duration
    FROM phone_calls
    JOIN people ON phone_calls.caller = people.phone_number
    WHERE phone_calls.year = 2024
    AND phone_calls.month = 7
    AND phone_calls.day = 28
    AND phone_calls.duration < 60) as phone_call
WHERE phone_number = phone_call.pcaller OR phone_number = phone_call.preceiver;

SELECT people.name,
phone_calls.caller,
phone_calls.receiver,
phone_calls.duration
FROM phone_calls
JOIN people ON phone_calls.caller = people.phone_number
WHERE phone_calls.year = 2024
AND phone_calls.month = 7
AND phone_calls.day = 28
AND phone_calls.duration < 60;

-- flight out of fiftyville
SELECT people.name, people.passport_number,
origin.full_name,
destination.full_name,
flights.month, flights.day
FROM people
JOIN passengers ON passengers.passport_number = people.passport_number
JOIN flights ON flights.id = passengers.flight_id
JOIN airports as origin ON origin.id = flights.origin_airport_id
JOIN airports as destination ON destination.id = flights.destination_airport_id
WHERE flights.year = 2024
AND flights.month = 7
AND flights.day >= 28
AND origin.full_name = 'Fiftyville Regional Airport';


-- left bakery between 10:15 to 10:25
SELECT bakery_security_logs.hour, bakery_security_logs.minute, bakery_security_logs.activity, bakery_security_logs.license_plate,
people.name
FROM bakery_security_logs
JOIN people ON bakery_security_logs.license_plate = people.license_plate
WHERE bakery_security_logs.year = 2024
AND bakery_security_logs.month = 7
AND bakery_security_logs.day = 28
AND bakery_security_logs.hour >= 10
AND bakery_security_logs.minute >= 15
AND bakery_security_logs.minute <= 25;

-- withdraw money at Leggett Street on 7/28
SELECT people.name,
atm_transactions.month, atm_transactions.day,
atm_transactions.amount, atm_transactions.transaction_type, atm_transactions.atm_location
FROM people
JOIN passengers ON passengers.passport_number = people.passport_number
JOIN bank_accounts ON bank_accounts.person_id = people.id
JOIN atm_transactions ON atm_transactions.account_number = bank_accounts.account_number
WHERE atm_transactions.year = 2024
AND atm_transactions.month = 7
AND atm_transactions.day = 28
AND atm_location = 'Leggett Street';

-- flight out of fiftyville
SELECT people.name, people.passport_number,
origin.full_name,
destination.full_name,
flights.month, flights.day
FROM people
JOIN passengers ON passengers.passport_number = people.passport_number
JOIN flights ON flights.id = passengers.flight_id
JOIN airports as origin ON origin.id = flights.origin_airport_id
JOIN airports as destination ON destination.id = flights.destination_airport_id
WHERE flights.year = 2024
AND flights.month = 7
AND flights.day >= 28
AND origin.full_name = 'Fiftyville Regional Airport'
ORDER BY flights.day ASC;






-- flight out of fiftyville
SELECT people.name, people.passport_number,
origin.full_name,
destination.full_name,
flights.month, flights.day
FROM people,
    (-- called each  other for less than a minute
    SELECT p1.name AS caller_name,
    p1.phone_number AS caller_number,
    p2.name AS receiver_name,
    p2.phone_number AS receiver_number,
    pc.duration
    FROM phone_calls pc
    JOIN people p1 ON pc.caller = p1.phone_number
    JOIN people p2 ON pc.receiver = p2.phone_number
    WHERE pc.year = 2024
    AND pc.month = 7
    AND pc.day = 28
    AND pc.duration < 60) as phone_call
JOIN passengers ON passengers.passport_number = people.passport_number
JOIN flights ON flights.id = passengers.flight_id
JOIN airports as origin ON origin.id = flights.origin_airport_id
JOIN airports as destination ON destination.id = flights.destination_airport_id
WHERE flights.year = 2024
AND flights.month = 7
AND flights.day >= 28
AND origin.full_name = 'Fiftyville Regional Airport'
AND (phone_call.caller_name = people.name
    OR phone_call.receiver_name = people.name)
ORDER BY flights.day ASC;

-- withdraw money at Leggett Street
SELECT bakery_security_logs.hour, bakery_security_logs.minute, bakery_security_logs.activity, bakery_security_logs.license_plate,
people.name, people.passport_number,
atm_transactions.amount, atm_transactions.transaction_type, atm_transactions.atm_location
FROM bakery_security_logs
JOIN people ON bakery_security_logs.license_plate = people.license_plate
JOIN passengers ON passengers.passport_number = people.passport_number
JOIN bank_accounts ON bank_accounts.person_id = people.id
JOIN atm_transactions ON atm_transactions.account_number = bank_accounts.account_number
WHERE bakery_security_logs.year = 2024
AND bakery_security_logs.month = 7
AND bakery_security_logs.day = 28
AND bakery_security_logs.hour >= 10
AND bakery_security_logs.minute >= 15
AND atm_transactions.year = 2024
AND atm_transactions.month = 7
AND atm_transactions.day = 28
AND atm_location = 'Leggett Street'
ORDER BY bakery_security_logs.hour, bakery_security_logs.minute

-- withdraw money at Leggett Street
SELECT bakery_security_logs.hour, bakery_security_logs.minute, bakery_security_logs.activity, bakery_security_logs.license_plate,
people.name, people.passport_number,
atm_transactions.amount, atm_transactions.transaction_type, atm_transactions.atm_location
FROM bakery_security_logs
JOIN people ON bakery_security_logs.license_plate = people.license_plate
JOIN passengers ON passengers.passport_number = people.passport_number
JOIN bank_accounts ON bank_accounts.person_id = people.id
JOIN atm_transactions ON atm_transactions.account_number = bank_accounts.account_number
WHERE bakery_security_logs.year = 2024
AND bakery_security_logs.month = 7
AND bakery_security_logs.day = 28
AND bakery_security_logs.hour >= 10
AND bakery_security_logs.minute >= 15
AND atm_transactions.year = 2024
AND atm_transactions.month = 7
AND atm_transactions.day = 28
AND atm_location = 'Leggett Street'
ORDER BY bakery_security_logs.hour, bakery_security_logs.minute;



SELECT *
FROM atm_transactions
WHERE atm_transactions.year = 2024
AND atm_transactions.month = 7
AND atm_transactions.day = 28
AND atm_location = 'Leggett Street';

SELECT * FROM bakery_security_logs
WHERE year = 2024
AND month = 7
AND day = 28
AND hour >= 10
AND minute >= 15;

SELECT * FROM crime_scene_reports
WHERE year = 2024
AND month = 7
AND day = 28
AND street = 'Humphrey Street';

SELECT * FROM interviews
WHERE id = 161
OR id = 162
OR id = 163;

