(define do-drive #t)

(define (init) #t)
(define (update)
  (if do-drive
      (if (drive 1 1 0 3)
          #t
          (set! do-drive #f))
      #t))