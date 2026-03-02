UPDATE categories
SET is_creator = true
WHERE id = '00000000-0000-0000-0000-000000000001';

UPDATE categories
SET is_creator = false
WHERE id != '00000000-0000-0000-0000-000000000001';